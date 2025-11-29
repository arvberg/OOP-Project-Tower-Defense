package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TowerDrawer {

    public static void drawTowers(List<Tower> towers, SpriteBatch batch){
        for (Tower tower : towers){
            float angleDeg = (float)Math.toDegrees(0);
            Vector2 p = tower.getPosition();
            Texture texture = tower.getTexture();
            TextureRegion region = new TextureRegion(texture);

            if(tower.isAiming()) {
                float dx = tower.getCurrentTarget().getX() - tower.getX();
                float dy = tower.getCurrentTarget().getY() - tower.getY();
                float angleRad = (float)Math.atan2(dy,dx);
                angleDeg = (float)Math.toDegrees(angleRad);
            }

            float dimensionX = tower.getDimension().x;
            float dimensionY = tower.getDimension().y;

            batch.draw(region, p.x - dimensionX/2f, p.y - dimensionY/2f,dimensionX/2, dimensionY/2, dimensionX, dimensionY, 1f, 1f, angleDeg);
        }
    }

    public static void drawPendingTower(Tower tower, SpriteBatch batch) {
        Vector2 p = tower.getPosition();
        Texture texture = tower.getTexture();

        float dimensionX = tower.getDimension().x;
        float dimensionY = tower.getDimension().y;

        batch.draw(texture, p.x - dimensionX/2f, p.y - dimensionY/ 2f , dimensionX, dimensionY);
    }

    public static void drawRange(Tower tower, SpriteBatch batch) {
            Vector2 p = tower.getPosition();

            TextureRegion texture = tower.getRangeTexture();

            float w = texture.getRegionWidth();
            float h = texture.getRegionHeight();

            float worldRadius = tower.getRange();  // i world units
            float textureRadius = w / 2f;          // i pixelvärldens world units
            float scale = worldRadius / textureRadius;

            batch.draw(texture, p.x -  w/2f, p.y - h/2f, w/2f, h / 2f,
                w, h, scale, scale, 0);
    }

}
