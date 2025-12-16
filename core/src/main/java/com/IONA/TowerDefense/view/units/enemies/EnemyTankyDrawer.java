package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.units.enemies.EnemyFast;
import com.IONA.TowerDefense.model.units.enemies.EnemyTanky;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public final class EnemyTankyDrawer implements DrawableEnemy {

    private static final Texture TEXTURE = new Texture(Assets.ENEMY_TANKY);

    private static final TextureRegion TEXTURE_R = new TextureRegion(TEXTURE);

    private final Rectangle hb;
    private final EnemyTanky enemy;

    public EnemyTankyDrawer(EnemyTanky enemy) {
        this.enemy = enemy;
        this.hb = enemy.getHitBox();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        float currentDir = enemy.getDegreeDirection() + 90f;
        batch.draw(
            TEXTURE_R,
            hb.x, hb.y,
            hb.width / 2f, hb.height / 2f,
            hb.width, hb.height,
            1.5f, 1.5f,
            currentDir
        );

    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }
}
