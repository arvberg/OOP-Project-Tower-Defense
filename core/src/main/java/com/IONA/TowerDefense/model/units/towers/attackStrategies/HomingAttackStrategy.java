package com.IONA.TowerDefense.model.units.towers.attackStrategies;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Missile;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
/**
 * HomingAttackStrategy fires homing missiles at target enemies.
 * <p>
 * Each attack creates a missile that continuously adjusts its direction
 * toward its assigned enemy, allowing it to track moving targets.
 * The missile’s damage and speed are determined by the firing tower.
 */
public class HomingAttackStrategy implements AttackStrategy {

    @Override
    public void attack(Tower tower, List<Enemy> targets, List<Projectile> projectiles) {
        for (Enemy e : targets) {

            Vector2 pDirection = VectorUtils.direction(tower.getPosition(), e.getPosition());

            float pSpeed = tower.getProjectileSpeed();
            int pDamage = tower.getDamage();
            Vector2 towerPos = new Vector2(tower.getPosition());
            Projectile p = new Missile(pDamage, pSpeed, towerPos, pDirection);
            p.setEnemyTarget(e);
            projectiles.add(p);
        }
    }
}
