package com.IONA.TowerDefense.view.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.TowerPulse;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class TowerPulseDrawer implements DrawableTower, AttackListener {

    private static final TextureAtlas ATLAS = new TextureAtlas(Gdx.files.internal(Assets.ANIMATION_ATLAS_PULSE));
    private static final Animation<TextureAtlas.AtlasRegion> PULSE_ANIMATION;

    private boolean pulseActive = false;
    private float pulseTime = 0f;

    static {
        PULSE_ANIMATION = new Animation<>(0.01f, ATLAS.findRegions("animation-collections-Animation_AoE_Pulse"));
        PULSE_ANIMATION.setPlayMode(Animation.PlayMode.LOOP);
    }

    private static final Texture TEXTURE = new Texture(Assets.TOWER_PULSE_BODY);
    private static final Texture TEXTURE_BARREL = new Texture(Assets.TOWER_BASIC_BARREL);
    private static final Texture TEXTURE_RANGE = new Texture(Assets.TOWER_RANGE);

    private static final TextureRegion TEXTURE_BARREL_R = new TextureRegion(TEXTURE_BARREL);
    private static final TextureRegion RANGE_REGION = new TextureRegion(TEXTURE_RANGE);

    private final TowerPulse tower;
    private Vector2 p;
    private float dimensionX;
    private float dimensionY;
    private float angleDeg;
    private float range;

    public TowerPulseDrawer(TowerPulse tower) {
        this.tower = tower;
        this.p = tower.getPosition();
        this.dimensionX = tower.getDimension().x;
        this.dimensionY = tower.getDimension().y;
        this.angleDeg = tower.getAngleDeg();
        this.range = tower.getRange();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {

        range = tower.getRange();
        p = tower.getPosition();

        if (pulseActive) {
            pulseTime += delta;

            TextureRegion frame = PULSE_ANIMATION.getKeyFrame(pulseTime);

            batch.draw(
                frame,
                p.x - range,
                p.y - range,
                range * 2,
                range * 2
            );

            if (PULSE_ANIMATION.isAnimationFinished(pulseTime)) {
                pulseActive = false;
            }
        }

        batch.draw(
            TEXTURE,
            p.x - dimensionX / 2f,
            p.y - dimensionY / 2f,
            dimensionX,
            dimensionY
        );
    }

    public void drawPendingTower(SpriteBatch batch) {
        p = tower.getPosition();

        batch.draw(
            TEXTURE,
            p.x - dimensionX / 2f,
            p.y - dimensionY / 2f,
            dimensionX,
            dimensionY
        );
    }


    public void drawRange(SpriteBatch batch) {

        float w = RANGE_REGION.getRegionWidth();
        float h = RANGE_REGION.getRegionHeight();

        float worldRadius = tower.getRange();
        float textureRadius = w / 2f;

        float scale = worldRadius / textureRadius;

        batch.draw(
            RANGE_REGION,
            p.x - w / 2f,
            p.y - h / 2f,
            w / 2f, h / 2f,
            w, h,
            scale, scale,
            0
        );
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
        TEXTURE_BARREL.dispose();
        TEXTURE_RANGE.dispose();
    }


    @Override
    public void onPulseActivated(Tower firingTower) {
        if (this.tower == firingTower) {
            pulseActive = true;
            pulseTime = 0f;
        }
    }
}
