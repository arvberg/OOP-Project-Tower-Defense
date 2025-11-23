package com.IONA.TowerDefense.model.units.decorations;

import com.IONA.TowerDefense.model.units.Unit;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Decoration extends Unit {
    protected Vector2 position = new Vector2();
    public Texture texture;

    public Decoration(){
        this.texture = new Texture("libgdx.png");
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition(){
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}
