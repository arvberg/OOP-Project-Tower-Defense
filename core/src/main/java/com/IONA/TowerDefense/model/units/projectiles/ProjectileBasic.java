package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.badlogic.gdx.math.Vector2;

/**
 * ProjectileBasic is a simple straight-moving projectile.
 * <p>
 * It travels in a fixed direction at a constant speed and deals damage
 * to enemies it hits. This class represents the most basic projectile
 * behavior used by towers.
 */
public class ProjectileBasic extends Projectile implements Movable {

    public ProjectileBasic(int damage, float speed, Vector2 position, Vector2 dxdy) {
        super(damage, speed, position, dxdy);
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.dxdy = dxdy;
        destroyed = false;
    }

    @Override
    public void move(float delta) {
        position.x += dxdy.x * speed * delta;
        position.y += dxdy.y * speed * delta;
    }
}
