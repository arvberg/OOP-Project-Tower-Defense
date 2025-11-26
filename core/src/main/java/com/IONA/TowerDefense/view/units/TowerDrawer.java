package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TowerDrawer {

    public static void drawTowers(List<Tower> towers, SpriteBatch batch){
        for (Tower t : towers){
            Vector2 p = t.getPosition();
            batch.draw(t.texture, p.x - 0.68f / 2f, p.y - 0.92f / 2f, 0.68f, 0.9f);
        }
    }

    public static void drawTower(Tower tower, SpriteBatch batch) {
        Vector2 p = tower.getPosition();
        batch.draw(tower.texture, p.x - 0.68f /2f, p.y - 0.9f / 2f , 0.68f, 0.9f);
    }



}
