package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.ProjectileFactory;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class AttackHandler {
    private final GameModel model;
    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private final List<Tower> towers;
    private final ProjectileFactory projectileFactory;
    private int money;

    public AttackHandler(GameModel model) {
        this.model = model;
        this.enemies = model.getEnemies();
        this.projectiles = model.getProjectiles();
        this.towers = model.getTowers();
        this.projectileFactory = new ProjectileFactory();
        this.money = model.getMoney();
        List<Unit> deadUnits = new ArrayList<>();
    }

    public void update() {
        updateTowers();
        updateProjectiles();
        removeDeadEnemies();
        removeDeadProjectiles();
    }

    public void updateTowers() {
        for (Tower tower : towers) {
            tower.update();
            if (tower.canShoot()) {
                List<Enemy> enemiesInRadius = enemiesInRadius(tower);
                List<Enemy> targets = tower.getTargets(enemiesInRadius);

                if (!targets.isEmpty()) {
                    towerAttack(tower, targets);
                    tower.resetCooldown();
                }
            }
        }
    }

    public void updateProjectiles() {
        for (Projectile projectile : projectiles) {

            if (projectile.isDestroyed()) {
                continue;
            }

            if (projectile.getProjectileType().equals("Homing")) {
                updateHomingProjectile(projectile);
            }
            projectile.move();
            projectileHit(projectile, enemies);
        }
    }

    public boolean withinRadius(Enemy enemy, Tower tower) {
        float distance = getDistance(enemy, tower);
        return distance < tower.getRange();
    }

    public void towerAttack(Tower tower, List<Enemy> enemies) {
        String attackType = tower.getAttackType();
        Projectile p = projectileFactory.createProjectile(attackType, tower, enemies);
        if (p != null) {
            projectiles.add(p);
        }
    }

    public List<Enemy> enemiesInRadius(Tower tower) {
        List<Enemy> enemiesInRadius = new ArrayList<>();
        for (Enemy e : enemies) {
            if (withinRadius(e, tower)) {
                enemiesInRadius.add(e);
            }
        }
        return enemiesInRadius;
    }

    public void updateHomingProjectile(Projectile p) {
        Enemy target = p.getEnemyTarget();
        if (target == null) {
            return;
        }

        float dist = getDistance(p, target);

        if (dist < 0.1f) { // threshold and snap
            p.setPosition(target.getX(), target.getY());
            p.setDestroyed(true);
            target.takeDamage(p.getDamage());
            return;
        }

        Vector2 dir = getDir(p, target);
        p.setDir(dir.x, dir.y);
    }

    public boolean isHit(Projectile projectile, Enemy enemy) {
        Rectangle hitbox = enemy.getHitBox();
        return hitbox.contains(projectile.getX(), projectile.getY());
    }

    public void projectileHit(Projectile projectile, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (isHit(projectile, enemy)) {
                projectile.setDestroyed(true);
                enemy.takeDamage(projectile.getDamage());

                break;
            }
        }
    }

    public void removeDeadEnemies() {
        if (enemies.isEmpty()) {
            return;
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHp() <= 0) {

                int moneyGained = enemies.get(i).getMoney();
                model.gainMoney(moneyGained);
                model.updateMoneyResource();

                enemies.remove(i);
            }
        }
    }

    public void removeDeadProjectiles() {
        if (projectiles.isEmpty()) {
            return;
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isDestroyed()) {
                projectiles.remove(i);
            }
        }
    }

    public Vector2 getDir(Unit from, Unit to) {
        float deltaX = to.getX() - from.getX();
        float deltaY = to.getY() - from.getY();

        float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return new Vector2(deltaX / length, deltaY / length);
    }

    public float getDistance(Unit from, Unit to) {
        float deltaX = to.getX() - from.getX();
        float deltaY = to.getY() - from.getY();

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY); // length
    }


}
