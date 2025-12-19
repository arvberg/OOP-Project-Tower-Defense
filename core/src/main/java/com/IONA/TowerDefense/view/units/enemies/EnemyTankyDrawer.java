package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.IONA.TowerDefense.model.units.enemies.EnemyFast;
import com.IONA.TowerDefense.model.units.enemies.EnemyTanky;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public final class EnemyTankyDrawer implements DrawableEnemy {

    private static final TextureAtlas ATLAS = new TextureAtlas(Gdx.files.internal(Assets.ANIMATION_ATLAS_BUG));
    private static final Animation<TextureAtlas.AtlasRegion> BUG_ANIMATION;


    static {
        BUG_ANIMATION = new Animation<>(0.02f, ATLAS.findRegions("animations-collections-bug-Bug"));
        BUG_ANIMATION.setPlayMode(Animation.PlayMode.LOOP);
    }

    private final Rectangle hb;
    private final HealthBar healthBar;
    private final EnemyTanky enemy;
    private float stateTime = 0f;

    public EnemyTankyDrawer(EnemyTanky enemy) {
        this.enemy = enemy;
        this.hb = enemy.getHitBox();
        this.healthBar = enemy.getHealthBar();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        float currentDir = enemy.getDegreeDirection() + 90f;
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion frame = BUG_ANIMATION.getKeyFrame(stateTime);
        batch.draw(
            frame,
            hb.x, hb.y,
            hb.width / 2f, hb.height / 2f,
            hb.width, hb.height,
            1.5f, 1.5f,
            currentDir
        );

    }

    public static void disposeStatic() {
        ATLAS.dispose();
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
}
