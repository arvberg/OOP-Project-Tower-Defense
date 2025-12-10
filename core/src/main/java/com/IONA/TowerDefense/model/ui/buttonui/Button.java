package com.IONA.TowerDefense.model.ui.buttonui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public abstract class Button {
    private final Vector2 buttonPosition;
    public float width;
    public float height;
    protected Rectangle bounds;


    private boolean buttonLocked = false;
    private final float lockedX;

    public Button(float x, float y, float width, float height){
        this.buttonPosition = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
        this.lockedX = 500f;
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

    public void toggleButton() {
        buttonLocked = !buttonLocked;
        if (buttonLocked) {
            model.getPlayButton().setButtonPosition(lockedX, 0);
            model.getSpeedUpButton().setButtonPosition(0, 0);
        } else {
            model.getPlayButton().setButtonPosition(0,0);
            model.getSpeedUpButton().setButtonPosition(lockedX, 0);
        }
    }

    public Vector2 getButtonPosition(){
        return buttonPosition;
    }

    public abstract void onClick();

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public float getWidth(){return this.width;}

    public float getHeight(){return this.height;}


}
