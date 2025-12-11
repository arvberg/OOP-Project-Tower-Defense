package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.ProjectileFactory;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AttackStrategy;
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

    public AttackHandler(GameModel model) {
        this.model = model;
        this.enemies = model.getEnemies();
        this.projectiles = model.getProjectiles();
        this.towers = model.getTowers();
        this.projectileFactory = new ProjectileFactory();
    }

    public void update(float delta) {
        updateTowers(delta);
        updateProjectiles(delta);
        removeDeadProjectiles();
    }

    public void updateTowers(float delta) {
        for (Tower tower : towers) {

            tower.updateCooldown(delta);

            List<Enemy> enemiesInRadius = enemiesInRadius(tower);
            List<Enemy> targets = tower.getTargets(enemiesInRadius);
            if (targets == null) {
                targets = new ArrayList<>();
            }

            boolean hasTargets = !targets.isEmpty();
            tower.setHasDetected(hasTargets);

            updateTowerAngle(tower, targets, delta);

            if (!hasTargets) {
                tower.setCurrentTarget(null);
            }

            if (hasTargets && tower.canShoot() && tower.getIsAiming()) {
                AttackStrategy strategy = tower.getAttackStrategy();
                strategy.attack(tower, targets, projectiles);
                tower.resetCooldown();
            }
        }
    }

    public void updateTowerAngle(Tower tower, List<Enemy> targets, float delta) {

        if (targets == null || targets.isEmpty()) {
            tower.setCurrentTarget(null);
            tower.setIsAiming(false);
            return;
        }

        float rotationSpeed = 10;

        Enemy target = targets.get(0);
        tower.setCurrentTarget(target);

        Vector2 desiredDir = VectorUtils.direction(tower.getPosition(), target.getPosition());
        Vector2 currentDir = tower.getDirection();

        if (currentDir == null) {
            tower.setDirection(desiredDir);
            tower.setIsAiming(false);
            return;
        }

        float r = rotationSpeed * delta;

        float xNew = currentDir.x + (desiredDir.x - currentDir.x) * r;
        float yNew = currentDir.y + (desiredDir.y - currentDir.y) * r;

        Vector2 newDir = new Vector2(xNew, yNew).nor();
        tower.setDirection(newDir);

        float dx = newDir.x - desiredDir.x;
        float dy = newDir.y - desiredDir.y;
        float distSq = dx * dx + dy * dy;

        boolean aimingDone = distSq < 0.001f;
        tower.setIsAiming(aimingDone);

    }

    public void updateProjectiles(float delta) {
        for (Projectile projectile : projectiles) {
            if (projectile.isDestroyed()) {
                continue;
            }
            projectile.move(delta);
            projectileHit(projectile, enemies);
        }
    }

    public boolean withinRadius(Enemy enemy, Tower tower) {
        float distance = VectorUtils.distance(enemy.getPosition(), tower.getPosition());
        return distance < tower.getRange();
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

        float distance = VectorUtils.distance(target.getPosition(), p.getPosition());

        if (distance < 0.1f) {
            p.setPosition(target.getX(), target.getY());
            p.setDestroyed(true);
            target.takeDamage(p.getDamage());
            return;
        }

        Vector2 dir = VectorUtils.direction(p.getPosition(), target.getPosition());
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
                return;
            }
        }
    }

    public void removeDeadProjectiles() {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            if (projectiles.get(i).isDestroyed()) {
                projectiles.remove(i);
            }
        }
    }

    public void removeAllProjectiles() {
        projectiles.clear();
    }

}
