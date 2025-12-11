package com.IONA.TowerDefense.view.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundDrawer {
    private static Texture backgroundTextureStarter = new Texture("Background_03.png");
    public BackgroundDrawer(){
    }

    public static void drawBackground(SpriteBatch batch, String background, float x, float y, float width, float height){
        if(background == "Starter map"){
            batch.draw(backgroundTextureStarter, x, y, width, height);
        }
    }
}
