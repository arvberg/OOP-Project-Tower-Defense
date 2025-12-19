package com.IONA.TowerDefense.view.units.projectiles;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public final class ProjectileBasicDrawer implements DrawableProjectile {

    private final Projectile projectile;
    private Vector2 p;

    private static final Texture TEXTURE = new Texture(Assets.PROJECTILE_BASIC);
    private static final TextureRegion TEXTURE_R = new TextureRegion(TEXTURE);

    public ProjectileBasicDrawer(Projectile projectile) {
        this.projectile = projectile;
        this.p = projectile.getPosition();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        p = projectile.getPosition();
        float angleDeg = VectorUtils.angleFromDirection(projectile.getDir());
        float distance = 0.48f;

        float width = 0.2f;
        float height = 0.2f;

        float x = p.x + MathUtils.cosDeg(angleDeg) * distance;
        float y = p.y + MathUtils.sinDeg(angleDeg) * distance;

        batch.draw(
            TEXTURE_R,
            x - width / 2f,
            y - height / 2f,
            width / 2f,
            height / 2f,
            width, height,
            0.6f, 1.3f,
            angleDeg - 90
        );
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }
}
