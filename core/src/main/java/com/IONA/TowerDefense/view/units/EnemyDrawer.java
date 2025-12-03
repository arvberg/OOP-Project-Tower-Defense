package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

public class EnemyDrawer {

    public static void drawEnemies(List<Enemy> enemies, SpriteBatch batch) {
        for (Enemy e : enemies) {
            Rectangle hb = e.getHitBox();
            if (e instanceof EnemyBasic) {


                batch.draw(
                    ((EnemyBasic) e).getTexture2(),
                    hb.x, hb.y,
                    hb.width / 2f, hb.height / 2f,   // origin
                    hb.width, hb.height,
                    1.5f, 1.5f,
                    ((EnemyBasic) e).getVisualRotationBack()
                );
                batch.draw(
                    ((EnemyBasic) e).getTexture1(),
                    hb.x, hb.y,
                    hb.width / 2f, hb.height / 2f,
                    hb.width, hb.height,
                    1.5f, 1.5f,
                    ((EnemyBasic) e).getVisualRotationFront()
                );
            }
            else {
                batch.draw(e.texture, hb.x, hb.y, hb.width, hb.height);
            }
        }
    }
}
