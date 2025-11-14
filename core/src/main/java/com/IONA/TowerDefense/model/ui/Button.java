package com.IONA.TowerDefense.model.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Button {
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

    public void render(SpriteBatch batch){
        batch.draw(texture, buttonPosition.x, buttonPosition.y, width, height);
    }

    public boolean isClicked(){
        if (Gdx.input.justTouched()){
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            return bounds.contains(touchX, touchY);
        }
        return false;
    }

    public void setPosition(float x, float y) {
        buttonPosition.set(x, y);
        bounds.setPosition(x, y);
    }

    public abstract void onClick();
}
