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

/**
 * Handles all tower attacks and projectile management in the game.
 * <p>
 * This class is responsible for updating towers and projectiles each frame,
 * determining which enemies are in range, executing attacks, and notifying
 * listeners about attack events such as projectile fired or pulse activated.
 */
public class AttackHandler {

    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private final List<Tower> towers;

    private final List<AttackListener> listeners = new ArrayList<>();

    private static final Vector2 IDLE_DIRECTION = new Vector2(-1f, 0f);
    private final Vector2 projectileBounderies = new Vector2(20f, 12f);

    /**
     * Creates a new AttackHandler with references to the game's towers, enemies, and projectiles.
     *
     * @param enemies list of all enemies in the game
     * @param projectiles list of all active projectiles
     * @param towers list of all towers
     */
    public AttackHandler(List<Enemy> enemies, List<Projectile> projectiles, List<Tower> towers) {
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.towers = towers;
    }

    /**
     * Updates all towers and projectiles, handles attacks, and removes destroyed projectiles.
     *
     * @param delta time elapsed since last update
     */
    public void update(float delta) {
        updateTowers(delta);
        updateProjectiles(delta);
        removeDeadProjectiles();
    }

    /**
     * Executes an attack for the given {@code tower} on the specified {@code targets}.
     * <p>
     * The tower's {@link AttackStrategy} is retrieved and executed.
     * The tower's cooldown is reset. If the tower is a {@link TowerPulse}, a pulse event is notified;
     * otherwise a projectile event is notified.
     *
     * @param tower the tower performing the attack
     * @param targets the enemies being targeted
     */
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

    /**
     * Updates all towers in the game.
     * <p>
     * For each tower:
     * <ul>
     *   <li>Updates the tower's internal state by calling {@link Tower#update(float)}.</li>
     *   <li>Finds all enemies within the tower's attack radius using {@link #enemiesInRadius(Tower)}.</li>
     *   <li>Selects targets using {@link Tower#getTargets(List)}.</li>
     *   <li>If the tower implements {@link Rotatable}, updates its rotation angle toward the selected target(s).</li>
     *   <li>If the tower has targets and can attack ({@link Tower#canAttack()}), executes an attack via {@link #towerAttack(Tower, List)}.</li>
     * </ul>
     *
     * @param delta the time elapsed since the last update, used for animations, cooldowns, and rotations
     */
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

            if (p.outOfBounds(projectileBounderies)) {
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
        float projectileX = projectile.getPosition().x;
        float projectileY = projectile.getPosition().y;
        return hitbox != null && hitbox.contains(projectileX, projectileY);
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

    /**
     * Notifies all registered {@link AttackListener}s that a projectile has been fired from a tower.
     *
     * @param tower the {@link Tower} that fired the projectile
     */
    public void notifyProjectileFired(Tower tower) {
        for (AttackListener l : listeners) {
            l.onProjectileFired(tower);
        }
    }

    /**
     * Notifies all registered {@link AttackListener}s that a pulse attack has been activated from a tower.
     *
     * @param tower the {@link Tower} that activated the pulse
     */
    public void notifyPulseActivated(Tower tower) {
        for (AttackListener l : listeners) {
            l.onPulseActivated(tower);
        }
    }

    public void addAttackListener(AttackListener l) {
        listeners.add(l);
    }


    public void removeAllProjectiles() {
        projectiles.clear();
    }
}
