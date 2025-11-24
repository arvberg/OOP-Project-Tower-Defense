package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class DecorationDrawer {

    public static void drawDecorations(List<Decoration> decorations, SpriteBatch batch){
        for (Decoration d: decorations){
            Vector2 p = d.getPosition();

            batch.draw(
                d.texture,
                p.x,
                p.y,
                d.getWidth(),
                d.getHeight());
        }

    }
}
