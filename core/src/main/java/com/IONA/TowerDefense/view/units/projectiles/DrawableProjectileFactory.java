package com.IONA.TowerDefense.view.units.projectiles;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.model.units.projectiles.Missile;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.projectiles.ProjectileBasic;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;
/**
 * Factory class for creating drawable representations of projectiles.
 *
 * Converts a given projectile (ProjectileBasic or Missile) into its corresponding
 * DrawableProjectile implementation for rendering purposes.
 */
public class DrawableProjectileFactory {

    private DrawableProjectileFactory() {
    }

    public static DrawableProjectile create(Unit projectile) {
        return switch (projectile) {
            case ProjectileBasic p -> new ProjectileBasicDrawer(p);
            case Missile p -> new ProjectileMissileDrawer(p);
            default -> throw new IllegalStateException("Unexpected value: " + projectile);
        };
    }
}
