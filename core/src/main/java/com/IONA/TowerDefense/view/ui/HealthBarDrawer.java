package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

public class HealthBarDrawer {

    public static void drawHealthBar(List<Enemy> enemies, ShapeRenderer shape) {

        for (Enemy e : enemies) {
            HealthBar hb = e.getHealthBar();
            Rectangle hitBoxDimensions = e.getHitBox();

            float percent = hb.getCurrentHealth() / (float) e.getMaxHp();
            if (percent < 1) {
                float x = e.getX() - hitBoxDimensions.getWidth();
                float y = e.getY() + hitBoxDimensions.getHeight();

                shape.setColor(Color.BLACK);
                shape.rect(x, y, hb.width, hb.height);

                shape.setColor(Color.RED);
                System.out.println(hb.width * percent);
                shape.rect(x, y, hb.width * percent, hb.height);
            }
        }
        shape.setColor(Color.WHITE);
    }
}
