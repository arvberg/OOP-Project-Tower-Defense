package com.IONA.TowerDefense.model.map;

import com.badlogic.gdx.graphics.Texture;

public class Background {

    public Texture BackgroundTexture;
    public Texture gameOverBackground;

    public Background() {
        this.BackgroundTexture = new Texture("Background_03.png");
        this.gameOverBackground = new Texture("Game_over_overlay_screen_01.png");

    }
}
