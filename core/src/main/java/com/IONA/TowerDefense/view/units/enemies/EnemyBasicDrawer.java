package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public final class EnemyBasicDrawer implements DrawableEnemy {

    private static final Texture TEXTURE_FRONT = new Texture(Assets.ENEMY_BASIC_FRONT);
    private static final Texture TEXTURE_BACK = new Texture(Assets.ENEMY_BASIC_BACK);
    private static final Texture TEXTURE_EYE = new Texture(Assets.ENEMY_BASIC_EYE);

    private static final TextureRegion TEXTURE_FRONT_R = new TextureRegion(TEXTURE_FRONT);
    private static final TextureRegion TEXTURE_BACK_R = new TextureRegion(TEXTURE_BACK);
    private static final TextureRegion TEXTURE_EYE_R = new TextureRegion(TEXTURE_EYE);

    private final Rectangle hb;
    private final HealthBar healthBar;
    private final EnemyBasic enemy;

    public EnemyBasicDrawer(EnemyBasic enemy) {
        this.enemy = enemy;
        this.hb = enemy.getHitBox();
        this.healthBar = enemy.getHealthBar();

    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer renderer, float delta) {
        float rotationFront = enemy.getVisualRotationFront();
        float rotationBack = enemy.getVisualRotationBack();

        batch.draw(
            TEXTURE_BACK_R,
            hb.x, hb.y,
            hb.width / 2f, hb.height / 2f,
            hb.width, hb.height,
            1.5f, 1.5f,
            rotationBack
        );

        batch.draw(
            TEXTURE_FRONT_R,
            hb.x, hb.y,
            hb.width / 2f, hb.height / 2f,
            hb.width, hb.height,
            1.5f, 1.5f,
            rotationFront
        );

        batch.draw(
            TEXTURE_EYE_R,
            hb.x, hb.y,
            hb.width / 2f, hb.height / 2f,
            hb.width, hb.height,
            1.5f, 1.5f,
            0
        );

    }

    @Override
    public void drawHealthBar(ShapeRenderer renderer, float delta) {
        float percent = healthBar.getCurrentHealth() / (float) enemy.getMaxHp();
        if (percent < 1) {
            float x = enemy.getX() - hb.getWidth();
            float y = enemy.getY() + hb.getHeight();

            renderer.setColor(Color.BLACK);
            renderer.rect(x, y, healthBar.width, healthBar.height);

            renderer.setColor(Color.RED);
            renderer.rect(x, y, healthBar.width * percent, healthBar.height);

            renderer.setColor(Color.WHITE);
        }
    }

    public static void disposeStatic() {
        TEXTURE_FRONT.dispose();
        TEXTURE_BACK.dispose();
    }

}
