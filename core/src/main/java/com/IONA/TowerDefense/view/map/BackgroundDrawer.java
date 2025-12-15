package com.IONA.TowerDefense.view.map;

import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundDrawer {
    private static Texture backgroundTextureStarter = new Texture(Assets.BACKGROUND_BASIC);
    public BackgroundDrawer(){
    }

    public static void drawBackground(SpriteBatch batch, String background, float x, float y, float width, float height){
        if(background == "Starter map"){
            batch.draw(backgroundTextureStarter, x, y, width, height);
        }
    }
}
