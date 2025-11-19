package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.models.RenderData;
import com.IONA.TowerDefense.model.units.interfaces.Renderable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Button implements Renderable {
    public Vector2 buttonPosition;
    public float width;
    public float height;
    protected Rectangle bounds;
    public Texture texture;

    public Button(String texturePath, float x, float y, float width, float height){
        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.buttonPosition = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public RenderData getRenderData() {
        return new RenderData(texture, buttonPosition.x, buttonPosition.y, width, height);
    }

    public void isClicked(float x, float y){
        /*if (Gdx.input.justTouched()){
            return bounds.contains(x, y);
        }
        return false;
        */

    }

    public void setButtonPosition(float x, float y) {
        buttonPosition.set(x, y);
        bounds.setPosition(x, y);
    }

    public Vector2 getButtonPosition(){
        return buttonPosition;
    }

    public abstract void onClick();
}
