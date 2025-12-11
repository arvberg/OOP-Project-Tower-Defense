package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public final class EnemyBasicDrawer implements DrawableEnemy {

    private static final Texture TEXTURE_FRONT = new Texture("Virus_front.png");
    private static final Texture TEXTURE_BACK = new Texture("Virus_green_back.png");

    private static final TextureRegion TEXTURE_FRONT_R = new TextureRegion(TEXTURE_FRONT);
    private static final TextureRegion TEXTURE_BACK_R = new TextureRegion(TEXTURE_BACK);

    private final Rectangle hb;
    private final EnemyBasic enemy;

    public EnemyBasicDrawer(EnemyBasic enemy) {
        this.enemy = enemy;
        this.hb = enemy.getHitBox();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
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
    }

    public static void disposeStatic() {
        TEXTURE_FRONT.dispose();
        TEXTURE_BACK.dispose();
    }
}
