package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class ProjectileFactory {

    public Vector2 getDir(Tower from, Enemy to) {
        float deltaX = to.getX() - from.getX();
        float deltaY = to.getY() - from.getY();
        float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return new Vector2(deltaX/length, deltaY/length);
    }

    public Projectile createProjectile(String type, Tower tower, List<Enemy> enemies) {

        if (enemies.isEmpty()) {
            return null;
        }

        switch (type) {
            case "HomingProjectile":
                Enemy target = enemies.get(0);
                Vector2 dir = getDir(tower, target);
                Vector2 start = new Vector2(tower.getX(), tower.getY());
                Projectile homing = new Projectile(
                    tower.getDamage(),
                    tower.getProjectileSpeed(), start, dir
                );
                homing.setEnemyTarget(target);
                return homing;

            default:
                throw new IllegalArgumentException("Unknown projectile type: " + type);
        }
    }
}
