package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;


public class Missile extends Projectile implements Movable {
    private float turnSpeed = 5f;
    private float acceleration = 8f;

    public Missile(int damage, float speed, Vector2 position, Vector2 dxdy) {
        super(damage, speed, position, dxdy);
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.dxdy = dxdy;
        this.destroyed = false;
        lifeSpan = 5f;
    }

    @Override
    public void move(float delta) {
        acceleration += 0.2f;

        if (enemyTarget != null) {
            Vector2 desiredDir = VectorUtils.direction(position, enemyTarget.getPosition());
            Vector2 currentDir = new Vector2(dxdy).nor();

            float xNew = currentDir.x + (desiredDir.x - currentDir.x) * turnSpeed * delta;
            float yNew = currentDir.y + (desiredDir.y - currentDir.y) * turnSpeed * delta;

            Vector2 smoothedDir = new Vector2(xNew, yNew).nor();
            setDir(smoothedDir.x, smoothedDir.y);
        }

        position.x += dxdy.x * (speed + acceleration) * delta;
        position.y += dxdy.y * (speed + acceleration) * delta;
    }
}
