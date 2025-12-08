package com.IONA.TowerDefense.view.units.towers;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class TowerBasicDrawer implements DrawableTower {

    private final TowerBasic tower;
    private Vector2 p;
    private Texture texture;
    private TextureRegion texture2;
    private TextureRegion rangetexture;
    private float dimensionX;
    private float dimensionY;
    private float angleDeg;

    public TowerBasicDrawer(TowerBasic tower){
        this.tower = tower;
        p = tower.getPosition();
        texture = tower.getTexture();
        texture2 = tower.getTexture2();
        rangetexture = tower.getRangeTexture();
        dimensionX = tower.getDimension().x;
        dimensionY = tower.getDimension().y;
        angleDeg = tower.getAngleDeg();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        angleDeg = tower.getAngleDeg();
        batch.draw(texture, p.x - dimensionX / 2f, p.y - dimensionY / 2f, dimensionX, dimensionY);
        batch.draw(texture2, p.x - dimensionX / 2f, p.y - dimensionY / 2f, dimensionX / 2f, dimensionY / 2f, dimensionX, dimensionY, 1f, 1f, angleDeg-90);
    }

    public void drawPendingTower(SpriteBatch batch) {
        p = tower.getPosition();
        batch.draw(texture, p.x - dimensionX/2f, p.y - dimensionY/ 2f , dimensionX, dimensionY);
        batch.draw(texture2, p.x - dimensionX / 2f, p.y - dimensionY / 2f, dimensionX / 2f, dimensionY / 2f, dimensionX, dimensionY, 1f, 1f, angleDeg-90);
    }

    public void drawRange(SpriteBatch batch) {

        float w = rangetexture.getRegionWidth();
        float h = rangetexture.getRegionHeight();
        float worldRadius = tower.getRange();  // i world units
        float textureRadius = w / 2f;          // i pixelvärldens world units
        float scale = worldRadius / textureRadius;

        batch.draw(rangetexture, p.x -  w/2f, p.y - h/2f, w/2f, h / 2f,
            w, h, scale, scale, 0);
    }
}
