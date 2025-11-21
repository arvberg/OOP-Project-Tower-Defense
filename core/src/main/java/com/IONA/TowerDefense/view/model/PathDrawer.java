package com.IONA.TowerDefense.view.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public class PathDrawer {

    public static void drawPath(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(Color.valueOf("#DFF8D0"));
        float width = 0.6f;
        for (int i = 0; i < model.getPath().getSegments().size() - 1; i++) {
            Vector2 a = model.getPath().getSegment(i).getStartPosition();
            Vector2 b = model.getPath().getSegment(i).getEndForDraw(width);
            shapeRenderer.rectLine((a.x), (a.y), (b.x), (b.y),width);
        }
    }

}
