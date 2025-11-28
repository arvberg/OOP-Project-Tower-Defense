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

    public static void drawTowers(List<Tower> towers, SpriteBatch batch) {
        for (Tower tower : towers) {
            Vector2 p = tower.getPosition();
            Texture texture = tower.getTexture();

            float size = 1f; // sätt denna variabel i varje torn?? hämta med getSize() ex.

            batch.draw(texture, p.x - size / 2f, p.y - size / 2f, size, size);
        }
    }

    public static void drawTower(Tower tower, SpriteBatch batch) {
        Vector2 p = tower.getPosition();
        Texture texture = tower.getTexture();

        float size = 1f;

        batch.draw(texture, p.x - size / 2f, p.y - size / 2f, size, size);
    }

    public static void drawRange(Tower tower, SpriteBatch batch) {
        Vector2 p = tower.getPosition();

        TextureRegion texture = tower.getRangeTexture();

        float w = texture.getRegionWidth();
        float h = texture.getRegionHeight();

        float worldRadius = tower.getRange();  // i world units
        float textureRadius = w / 2f;          // i pixelvärldens world units
        float scale = worldRadius / textureRadius;

        batch.draw(texture, p.x - w / 2f, p.y - h / 2f, w / 2f, h / 2f,
            w, h, scale, scale, 0);
    }

}
