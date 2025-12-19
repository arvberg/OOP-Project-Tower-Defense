package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.model.units.projectiles.Missile;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Rotatable;
import com.IONA.TowerDefense.model.units.towers.Tower;
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

    private static final Vector2 IDLE_DIRECTION = new Vector2(-1f, 0f);
    private final Vector2 mapDimensions = new Vector2(16f, 9f);

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

    public void towerAttack(Tower tower, List<Enemy> targets) {
        AttackStrategy strategy = tower.getAttackStrategy();
        strategy.attack(tower, targets, projectiles);
        tower.resetCooldown();

        if (tower instanceof TowerPulse) {
            notifyPulseActivated(tower);
        } else {
            notifyProjectileFired(tower);
        }
    }

    public void updateTowers(float delta) {
        for (Tower tower : towers) {
            if (tower == null) continue;

            tower.update(delta);

            List<Enemy> enemiesInRadius = enemiesInRadius(tower);
            List<Enemy> targets = tower.getTargets(enemiesInRadius); // never null

            if (tower instanceof Rotatable r) {
                updateTowerAngle(r, tower, targets, delta);
            }

            if (!targets.isEmpty() && tower.canAttack()) {
                towerAttack(tower, targets);
            }
        }
    }

    private void updateTowerAngle(Rotatable r, Tower tower, List<Enemy> targets, float delta) {
        if (targets.isEmpty()) {
            if (tower.getCooldown() <= -1f) {
                r.setDesiredDirection(new Vector2(IDLE_DIRECTION));
                r.rotateTower(delta);
            }
            return;
        }

        Enemy target = targets.get(0);

        Vector2 desiredDir = VectorUtils.direction(tower.getPosition(), target.getPosition());
        if (desiredDir == null || (desiredDir.x == 0f && desiredDir.y == 0f)) {
            desiredDir = new Vector2(IDLE_DIRECTION);
        }

        r.setDesiredDirection(desiredDir);
        r.rotateTower(delta);
    }

    public void updateProjectiles(float delta) {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            if (p == null) continue;

            if (p.outOfBounds(mapDimensions)) {
                p.setDestroyed(true);
            }

            if (p.isDestroyed()) continue;

            // Missiles kan behöva nytt mål
            if (p instanceof Missile missile) {
                handleMissileTarget(missile);
                if (missile.isDestroyed()) continue;
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
        if (pos == null) return null;

        for (Enemy e : enemies) {
            if (e == null || e.isDead()) continue;

            Vector2 ePos = e.getPosition();
            float dx = ePos.x - pos.x;
            float dy = ePos.y - pos.y;
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
        List<Enemy> result = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e == null) continue;
            if (withinRadius(e, tower)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean isHit(Projectile projectile, Enemy enemy) {
        Rectangle hitbox = enemy.getHitBox();
        return hitbox != null && hitbox.contains(projectile.getX(), projectile.getY());
    }

    public void projectileHit(Projectile projectile, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy == null || enemy.isDead()) continue;

            if (isHit(projectile, enemy)) {
                projectile.setDestroyed(true);
                enemy.takeDamage(projectile.getDamage());
                return;
            }
        }
    }

    public void removeDeadProjectiles() {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            if (p != null && p.isDestroyed()) {
                projectiles.remove(i);
            }
        }
    }

    public void removeAllProjectiles() {
        projectiles.clear();
    }

    public void notifyProjectileFired(Tower tower) {
        for (AttackListener l : listeners) {
            l.onProjectileFired(tower);
        }
    }

    public void notifyPulseActivated(Tower tower) {
        for (AttackListener l : listeners) {
            l.onPulseActivated(tower);
        }
    }

    public void addAttackListener(AttackListener l) {
        listeners.add(l);
    }
}
