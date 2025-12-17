package com.IONA.TowerDefense.view.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public final class TowerBasicDrawer implements DrawableTower, AttackListener {

    private static final Texture TEXTURE = new Texture(Assets.TOWER_BASIC_BODY);
    private static final Texture TEXTURE_BARREL = new Texture(Assets.TOWER_BASIC_BARREL);
    private static final Texture TEXTURE_RANGE = new Texture(Assets.TOWER_RANGE);

    private static final TextureRegion TEXTURE_BARREL_R = new TextureRegion(TEXTURE_BARREL);
    private static final TextureRegion RANGE_REGION = new TextureRegion(TEXTURE_RANGE);

    private static final TextureAtlas ATLAS = new TextureAtlas(Gdx.files.internal(Assets.ANIMATION_ATLAS_TOWERBASICFIRE));
    private static final Animation<TextureAtlas.AtlasRegion> TOWERBASICFIRE_ANIMATION;

    private boolean pulseActive = false;
    private float pulseTime = 0f;

    static {
        TOWERBASICFIRE_ANIMATION = new Animation<>(0.01f, ATLAS.findRegions("animation-collections-Animation_TowerBasic_Fire"));
        TOWERBASICFIRE_ANIMATION.setPlayMode(Animation.PlayMode.LOOP);
    }

    private final TowerBasic tower;
    private Vector2 p;
    private float dimensionX;
    private float dimensionY;
    private float angleDeg;

    public TowerBasicDrawer(TowerBasic tower){
        this.tower = tower;
        this.p = tower.getPosition();
        this.dimensionX = tower.getDimension().x;
        this.dimensionY = tower.getDimension().y;
        this.angleDeg = tower.getAngleDeg();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        angleDeg = tower.getAngleDeg();

        if (pulseActive) {
            pulseTime += delta;

            TextureRegion frame = TOWERBASICFIRE_ANIMATION.getKeyFrame(pulseTime);

            float distance = 0.70f;

            float width = 0.2f;
            float height = 0.2f;

            float x = p.x + MathUtils.cosDeg(angleDeg) * distance;
            float y = p.y + MathUtils.sinDeg(angleDeg) * distance;

            batch.draw(
                frame,
                x - width / 2f,
                y - height / 2f,
                width / 2f,
                height / 2f,
                width, height,
                0.86f, 3.2f,
                angleDeg-90
            );


            if (TOWERBASICFIRE_ANIMATION.isAnimationFinished(pulseTime)) {
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

        batch.draw(
            TEXTURE_BARREL_R,
            p.x - dimensionX / 2f,
            p.y - dimensionY / 2f,
            dimensionX / 2f, dimensionY / 2f,
            dimensionX, dimensionY,
            1.3f, 1.3f,
            angleDeg-90
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

        batch.draw(
            TEXTURE_BARREL_R,
            p.x - dimensionX / 2f,
            p.y - dimensionY / 2f,
            dimensionX / 2f, dimensionY / 2f,
            dimensionX, dimensionY,
            1.3f, 1.3f,
            angleDeg
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
    public void onProjectileFired() {
        pulseActive = true;
        pulseTime = 0f;
    }

    @Override
    public void onPulseActivated() {
    }
}
