package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.model.units.projectiles.Missile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.TowerPulse;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AttackStrategy;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class AttackHandler {

    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private final List<Tower> towers;
    private final List<AttackListener> listeners = new ArrayList<>();

    public AttackHandler(List<Enemy> enemies, List<Projectile> projectiles, List<Tower> towers) {
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.towers = towers;
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

            if (hasTargets && tower.hasCooledDown() && tower.getIsAiming()) {
                AttackStrategy strategy = tower.getAttackStrategy();
                strategy.attack(tower, targets, projectiles);
                tower.resetCooldown();
                if (tower instanceof TowerPulse) {
                    notifyPulseActivated();
                }
                else {
                    notifyProjectileFired();
                }
            }

        }
    }

    public void updateTowerAngle(Tower tower, List<Enemy> targets, float delta) {

        if (targets == null || targets.isEmpty()) {
            tower.setCurrentTarget(null);
            tower.setIsAiming(false);
            return;
        }

        float rotationSpeed = tower.getRotationSpeed();

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

        boolean aimingDone = distSq < tower.getAimingMargin();
        tower.setIsAiming(aimingDone);
    }

    public void updateProjectiles(float delta) {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);

            if (p.isDestroyed()) continue;

            if (p instanceof Missile missile) {
                handleMissileTarget(missile);
            }

            p.move(delta);
            projectileHit(p, enemies);
        }
    }

    private void handleMissileTarget(Missile missile) {

        Enemy target = missile.getEnemyTarget();

        if (target == null || target.isDead() || !enemies.contains(target)) {

            Enemy newTarget = findNewTarget(missile);

            missile.setEnemyTarget(newTarget);

            if (newTarget == null) {
                missile.setDestroyed(true);
            }
        }
    }

    private Enemy findNewTarget(Missile missile) {
        Enemy closest = null;
        float minDistSq = Float.MAX_VALUE;

        Vector2 pos = missile.getPosition();

        for (Enemy e : enemies) {
            if (e.isDead()) continue;

            float dx = e.getPosition().x - pos.x;
            float dy = e.getPosition().y - pos.y;
            float distSq = dx * dx + dy * dy;

            if (distSq < minDistSq) {
                minDistSq = distSq;
                closest = e;
            }
        }
        return closest;
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


    public void notifyProjectileFired() {
        for (AttackListener l : listeners) {
            l.onProjectileFired();
        }
    }

    public void notifyPulseActivated() {
        for (AttackListener l : listeners) {
            l.onPulseActivated();
        }
    }

    public void addAttackListener(AttackListener l) {
        listeners.add(l);
    }

    public void removeAttackListener(AttackListener l) {
        listeners.remove(l);
    }
}
