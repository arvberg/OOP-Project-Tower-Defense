package com.IONA.TowerDefense.model.ui.buttonui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Button {
    private final Vector2 buttonPosition;
    public float width;
    public float height;
    public float x;
    public float y;
    protected Rectangle bounds;
    public Texture texture;

    public Button(String texturePath, float x, float y, float width, float height){
        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.buttonPosition = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }


    public void isClicked(Vector2 pos){
        if(bounds.contains(pos)){
            onClick();
        }
    }

    public void setButtonPosition(float x, float y) {
        buttonPosition.set(x, y);
        bounds.setPosition(x, y);
    }

    public Vector2 getButtonPosition(){
        return buttonPosition;
    }

    public abstract void onClick();

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }
}
