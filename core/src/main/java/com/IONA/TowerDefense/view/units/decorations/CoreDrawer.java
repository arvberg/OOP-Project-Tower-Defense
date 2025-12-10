package com.IONA.TowerDefense.view.units.decorations;

import com.IONA.TowerDefense.model.units.decorations.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public final class CoreDrawer implements DrawableDecoration{
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;
    private final Core core;

    private TextureAtlas atlas;
    private Animation<TextureAtlas.AtlasRegion> coreAnimation;
    private float stateTime = 0f;

    public CoreDrawer(Core core){
        this.core = core;
        this.dimensionX = core.getWidth();
        this.dimensionY = core.getHeight();
        this.p = core.getPosition();

        atlas = new TextureAtlas(Gdx.files.internal("atlas/core_animation.atlas"));
        coreAnimation = new Animation<>(0.01f, atlas.findRegions("Core_01"));
        coreAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion frame = coreAnimation.getKeyFrame(stateTime);
        batch.draw(
            frame,
            p.x - dimensionX/2f,
            p.y - dimensionY/2f,
            dimensionX,
            dimensionY
        );
    }
}
