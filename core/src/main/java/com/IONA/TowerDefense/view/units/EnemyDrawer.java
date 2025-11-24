package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

public class EnemyDrawer {

    public static void drawEnemies(List<Enemy> enemies, SpriteBatch batch){

        for (Enemy e : enemies) {
            Rectangle hb = e.getHitBox();
            batch.draw(e.texture, hb.x, hb.y, hb.width, hb.height);
        }
    }
}
