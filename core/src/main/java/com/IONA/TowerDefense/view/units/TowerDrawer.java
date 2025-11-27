package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TowerDrawer {

    public static void drawTowers(List<Tower> towers, SpriteBatch batch){
        for (Tower t : towers){
            Vector2 p = t.getPosition();
            batch.draw(t.texture, p.x - 0.68f / 2f, p.y - 0.92f / 2f, 0.9f, 0.9f);
        }
    }

    public static void drawTower(Tower tower, SpriteBatch batch) {
        Vector2 p = tower.getPosition();
        batch.draw(tower.texture, p.x - 0.68f /2f, p.y - 0.9f / 2f , 0.9f, 0.9f);
    }

    public static void drawRange(Tower tower, SpriteBatch batch) {
            Vector2 p = tower.getPosition();

            TextureRegion region = tower.getRangeTexture();

            float w = region.getRegionWidth();
            float h = region.getRegionHeight();

            float worldRadius = tower.getRange();  // i world units
            float textureRadius = w / 2f;          // i pixelvärldens world units
            float scale = worldRadius / textureRadius;

            batch.draw(tower.getRangeTexture(), p.x -  w/2f, p.y - h/2f, w/2f, h / 2f,
                w, h, scale, scale, 0);
    }

}
