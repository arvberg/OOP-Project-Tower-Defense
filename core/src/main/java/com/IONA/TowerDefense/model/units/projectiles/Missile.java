package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.badlogic.gdx.math.Vector2;


public class Missile extends Projectile implements Movable {
    private float acceleration = 0;

    public Missile(int damage, float speed, Vector2 position, Vector2 dxdy) {
        super(damage, speed, position, dxdy);
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.dxdy = dxdy;
        this.destroyed = false;
    }

    @Override
    public void move(float delta) {
        acceleration += 0.2f;
        Vector2 newDir = VectorUtils.direction(position, enemyTarget.getPosition());
        setDir(newDir.x, newDir.y);
        position.x += dxdy.x * (speed + acceleration) * delta;
        position.y += dxdy.y * (speed + acceleration) * delta;
    }
}
