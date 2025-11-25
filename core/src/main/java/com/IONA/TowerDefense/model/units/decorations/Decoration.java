package com.IONA.TowerDefense.model.units.decorations;

import com.IONA.TowerDefense.model.units.Unit;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Decoration extends Unit {
    protected Vector2 position = new Vector2();
    public Texture texture;
    public float width;
    public float height;
    public Rectangle hitBox;

    public Decoration(){
        this.texture = new Texture("libgdx.png");
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
        setHitBox(width, height);
    }

    public Vector2 getPosition(){
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public void setHitBox(float width, float height) {


        float newX = (position.x - width/2);
        float newY = (position.y - height/2);


        if (hitBox == null) {
            hitBox = new Rectangle(newX, newY, width, height);
        } else {
            hitBox.set(newX, newY, width, height);
        }
    }
}
