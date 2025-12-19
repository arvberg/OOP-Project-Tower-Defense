package com.IONA.TowerDefense.model.units.decorations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Core extends Decoration{

    public Core(){
        this.width = 3.75f;
        this.height = 1.5f;
        setHitBox(0.1f,0.1f);
    }

    public Vector2 getDimension() {
        return new Vector2(width, height);
    }
}

