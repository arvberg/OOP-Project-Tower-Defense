package com.IONA.TowerDefense.model.units.decorations;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;


public class Core extends Decoration{

    public Core(){
        this.width = 3f;
        this.height = 1.5f;
        this.texture = new Texture("Core_temp_01.png");
        setHitBox(0.1f,0.1f);
    }
}

