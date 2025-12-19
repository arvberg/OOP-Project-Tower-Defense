package com.IONA.TowerDefense.model.units.towers.attackStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.List;
/**
 * AreaAttackStrategy applies damage directly to all enemies within range.
 * <p>
 * This strategy does not create projectiles; instead, it instantly damages
 * every target enemy, making it suitable for pulse or area-of-effect towers.
 */
public class AreaAttackStrategy implements AttackStrategy {

    @Override
    public void attack(Tower tower, List<Enemy> targets, List<Projectile> projectiles) {
        int damage = tower.getDamage();
        for (Enemy e : targets) {
            e.takeDamage(damage);
        }
    }
}
