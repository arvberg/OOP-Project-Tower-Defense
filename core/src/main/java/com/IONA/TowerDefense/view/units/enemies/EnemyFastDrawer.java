package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.model.units.enemies.EnemyFast;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public final class EnemyFastDrawer implements DrawableEnemy {

    private static final Texture TEXTURE = new Texture(Assets.ENEMY_FAST);

    private final Rectangle hb;
    private final EnemyFast enemy;

    public EnemyFastDrawer(EnemyFast enemy) {
        this.enemy = enemy;
        this.hb = enemy.getHitBox();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){

        batch.draw(
            TEXTURE,
            hb.x, hb.y,
            hb.width, hb.height
        );

    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }
}
