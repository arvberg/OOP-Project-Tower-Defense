package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public final class EnemyBasicDrawer implements DrawableEnemy {

    private final TextureRegion texture2;
    private final TextureRegion texture1;
    private final Rectangle hb;
    private final EnemyBasic enemy;

    public EnemyBasicDrawer(EnemyBasic enemy) {
        this.enemy = enemy;
        texture1 = enemy.getTexture1();
        texture2 = enemy.getTexture2();
        hb = enemy.getHitBox();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        float rotationfront = enemy.getVisualRotationFront();
        float rotationback = enemy.getVisualRotationBack();

        batch.draw(
            texture2,
            hb.x, hb.y,
            hb.width / 2f, hb.height / 2f,
            hb.width, hb.height,
            1.5f, 1.5f,
            rotationback
        );
        batch.draw(
            texture1,
            hb.x, hb.y,
            hb.width / 2f, hb.height / 2f,
            hb.width, hb.height,
            1.5f, 1.5f,
            rotationfront
        );

    }
}
