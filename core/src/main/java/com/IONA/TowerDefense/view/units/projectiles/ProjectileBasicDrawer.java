package com.IONA.TowerDefense.view.units.projectiles;

import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class ProjectileBasicDrawer implements DrawableProjectile {

    private final Projectile projectile;
    private Vector2 p;
    private Texture texture;

    public ProjectileBasicDrawer(Projectile projectile){
        this.projectile = projectile;
        p = projectile.getPosition();
        texture = projectile.getIcon();
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        p = projectile.getPosition();
        batch.draw(texture, p.x, p.y, 0.18f, 0.18f);
    }

}
