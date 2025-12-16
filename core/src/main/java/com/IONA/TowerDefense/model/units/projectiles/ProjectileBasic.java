package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class ProjectileBasic extends Projectile implements Movable {
    protected int damage;
    protected float speed;
    protected Vector2 position;
    protected Vector2 dxdy;
    protected Enemy enemyTarget;
    protected boolean destroyed;
    private Vector2 dimension;
    private String projectileType = "Basic";

    public ProjectileBasic (int damage, float speed, Vector2 position, Vector2 dxdy) {
        super(damage,speed,position,dxdy);
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.dxdy = dxdy;
        this.destroyed = false;
    }

    @Override
    public void move(float delta) {
        position.x += dxdy.x * speed * delta;
        position.y += dxdy.y * speed * delta;
    }
}
