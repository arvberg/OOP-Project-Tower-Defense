package com.IONA.TowerDefense.view.map;

import com.IONA.TowerDefense.model.map.Background;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundDrawer {
    private static Texture backgroundTexture = new Texture("Background_03.png");
    public BackgroundDrawer(){
    }

    public static void drawBackground(SpriteBatch batch, Background background, float x, float y, float width, float height){
        String map = background.getMap();
        if(map == "Bacis"){
            batch.draw(backgroundTexture, x, y, width, height);
        }
    }
}
