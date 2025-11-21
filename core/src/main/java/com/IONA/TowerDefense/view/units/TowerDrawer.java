package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TowerDrawer {

    public static void drawTowers(List<Tower> towers, SpriteBatch batch){
        for (Tower t : towers){
            Vector2 p = t.getPosition();
            batch.draw(t.texture, p.x, p.y, 0.5f, 0.5f);
        }
    }

}
