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
    private Texture texture = new Texture("Tower_back.png");
    private Texture texture2 = new Texture("TowerBasic_01_barrel.png");
    private TextureRegion texture2r = new TextureRegion(texture2);
    private TextureRegion rangeTexture = new TextureRegion(new Texture("Range_01.png"));
    private float dimensionX;
    private float dimensionY;
    private float angleDeg;

    public TowerBasicDrawer(TowerBasic tower){
        this.tower = tower;
        p = tower.getPosition();
        dimensionX = tower.getDimension().x;
        dimensionY = tower.getDimension().y;
        angleDeg = tower.getAngleDeg();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        angleDeg = tower.getAngleDeg();
        batch.draw(texture, p.x - dimensionX / 2f, p.y - dimensionY / 2f, dimensionX, dimensionY);
        batch.draw(texture2r, p.x - dimensionX / 2f, p.y - dimensionY / 2f, dimensionX / 2f, dimensionY / 2f, dimensionX, dimensionY, 1f, 1f, angleDeg-90);
    }

    public void drawPendingTower(SpriteBatch batch) {
        p = tower.getPosition();
        batch.draw(texture, p.x - dimensionX/2f, p.y - dimensionY/ 2f , dimensionX, dimensionY);
        batch.draw(texture2r, p.x - dimensionX / 2f, p.y - dimensionY / 2f, dimensionX / 2f, dimensionY / 2f, dimensionX, dimensionY, 1f, 1f, angleDeg-90);
    }

    public void drawRange(SpriteBatch batch) {

        float w = rangeTexture.getRegionWidth();
        float h = rangeTexture.getRegionHeight();
        float worldRadius = tower.getRange();  // i world units
        float textureRadius = w / 2f;          // i pixelvärldens world units
        float scale = worldRadius / textureRadius;

        batch.draw(rangeTexture, p.x -  w/2f, p.y - h/2f, w/2f, h / 2f,
            w, h, scale, scale, 0);
    }
}
