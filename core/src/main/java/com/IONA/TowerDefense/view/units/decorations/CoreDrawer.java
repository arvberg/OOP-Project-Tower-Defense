package com.IONA.TowerDefense.view.units.decorations;

import com.IONA.TowerDefense.model.units.decorations.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class CoreDrawer implements DrawableDecoration {

    // -----------------------------
    // STATIC TEXTURES / ANIMATION
    // -----------------------------
    private static final TextureAtlas ATLAS = new TextureAtlas(Gdx.files.internal("atlas/core_animation.atlas"));
    private static final Animation<TextureAtlas.AtlasRegion> CORE_ANIMATION;

    static {
        CORE_ANIMATION = new Animation<>(0.01f, ATLAS.findRegions("Core_01"));
        CORE_ANIMATION.setPlayMode(Animation.PlayMode.LOOP);
    }

    // -----------------------------
    // INSTANCE DATA
    // -----------------------------
    private final Core core;
    private final float dimensionX;
    private final float dimensionY;
    private Vector2 p;
    private float stateTime = 0f;


    public CoreDrawer(Core core) {
        this.core = core;
        this.dimensionX = core.getWidth();
        this.dimensionY = core.getHeight();
        this.p = core.getPosition();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion frame = CORE_ANIMATION.getKeyFrame(stateTime);
        batch.draw(
            frame,
            p.x - dimensionX / 2f,
            p.y - dimensionY / 2f,
            dimensionX,
            dimensionY
        );
    }

    public static void disposeStatic() {
        ATLAS.dispose();
    }
}
