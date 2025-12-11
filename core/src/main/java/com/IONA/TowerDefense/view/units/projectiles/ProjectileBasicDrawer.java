package com.IONA.TowerDefense.view.units.projectiles;

import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class ProjectileBasicDrawer implements DrawableProjectile {

    private final Projectile projectile;
    private Vector2 p;

    private static final Texture TEXTURE = new Texture("Projectile_basic_01.png");

    public ProjectileBasicDrawer(Projectile projectile){
        this.projectile = projectile;
        this.p = projectile.getPosition();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        p = projectile.getPosition();
        batch.draw(TEXTURE, p.x, p.y, 0.18f, 0.18f);
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }
}
