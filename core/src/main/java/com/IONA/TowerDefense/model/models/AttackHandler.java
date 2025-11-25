package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class AttackHandler {
    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private final List<Tower> towers;

    public AttackHandler(GameModel model) {
        this.enemies = model.getEnemies();
        this.projectiles = model.getProjectiles();
        this.towers = model.getTowers();
        List<Unit> deadUnits = new ArrayList<>();
    }

    public void update() {
        for (Tower tower : towers) {
            tower.update();

                // tower.addTimeSinceLastShot(delta);
                for (Enemy enemy : enemies) {
                    if (withinRadius(enemy, tower) && tower.canShoot()) {
                        fireHomingProjectile(enemy, tower);
                        tower.resetCooldown();
                        break;
                    }
                }
            }
            for (Projectile projectile : projectiles) {
                updateHomingProjectile(projectile);
                projectile.move();
            }
    }

    public boolean withinRadius(Enemy enemy, Tower tower) {
        float distance = getDistance(enemy, tower);
        return distance < tower.getRange();
    }
/*
    public void fireProjectile(Enemy target, Tower tower) {
        int damage = tower.getDamage();
        float projectileSpeed = tower.getProjectileSpeed();
        float fireRate = tower.getFireRate();
        float towerX = tower.getPosition().x;
        float towerY = tower.getPosition().y;
        float length = getDistance(target, tower);

        // temporary target for demo, change to target later
        float dirX = getDir(enemies.get(0), tower).x;
        float dirY = getDir(enemies.get(0), tower).y;

        //float spawnTime = cumulativeDelay;
        // projectiles.add(new Projectile(damage, projectileSpeed, towerX, towerY, dirX, dirY, enemies.get(0)));
    }

 */

    public void fireHomingProjectile(Enemy target, Tower tower) {
        int damage = tower.getDamage();
        float projectileSpeed = tower.getProjectileSpeed();
        Vector2 towerPos =  tower.getPosition();

        Vector2 dir = getDir(tower, target);

        Projectile projectile = new Projectile(damage, projectileSpeed, towerPos, dir);
        projectile.setEnemyTarget(target);
        projectiles.add(projectile);
    }

    public void updateHomingProjectile(Projectile p) {
        Enemy target =  p.getEnemyTarget();
        if (target == null) {
            return;
        }
        Vector2 dir = getDir(p, target);
        p.setDir(dir.x, dir.y);
    }

    public boolean isHit(Projectile projectile, Enemy enemy) {
        Rectangle hitbox = enemy.getHitBox();
        return hitbox.contains(projectile.getX(), projectile.getY());
    }

    public void projectileHit(Projectile projectile, Enemy enemy) {
        if (isHit(projectile, enemy)) {
            projectiles.remove(projectile);
            enemy.takeDamage(projectile.getDamage());
        }
    }

    /*
    public void setProjectileDir(Projectile projectile) {
        float posX = projectile.getY();
        float posY = projectile.getY();

        float dx = posX - 1;
        float dy = posY - 1;

        float length = (float) Math.sqrt(dx * dx + dy * dy);
        float dirX = dx / length;
        float dirY = dy / length;

        projectile.setDir(dirX, dirY);
    }

     */

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public Vector2 getDir(Unit from, Unit to) {
        float deltaX = to.getX() - from.getX();
        float deltaY = to.getY() - from.getY();

        float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return new Vector2(deltaX/length, deltaY/length);
    }

    public float getDistance (Unit from, Unit to) {
        float deltaX = to.getX() - from.getX();
        float deltaY = to.getY() - from.getY();

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY); // length
    }



}
