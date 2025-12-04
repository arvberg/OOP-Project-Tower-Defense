package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.view.wrappers.units.DrawableEnemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class EnemyBasicDrawer extends DrawableEnemy {

    private TextureRegion texture2;
    private TextureRegion texture1;
    private Rectangle hb;
    private float rotationfront;
    private float rotationback;

    public EnemyBasicDrawer(EnemyBasic enemy) {
        super(enemy);
        texture1 = enemy.getTexture1();
        texture2 = enemy.getTexture2();
        hb = enemy.getHitBox();
        rotationfront = enemy.getVisualRotationFront();
        rotationback = enemy.getVisualRotationBack();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
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
