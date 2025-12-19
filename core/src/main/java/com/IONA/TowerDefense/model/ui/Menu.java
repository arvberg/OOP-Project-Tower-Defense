package com.IONA.TowerDefense.model.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Menu {
    public Vector2 menuPosition;
    protected float width;
    protected float height;
    protected Rectangle bounds;

    public Menu(float x, float y, float width, float height) {
        this.menuPosition = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(float x, float y) {
        menuPosition.x = x;
        menuPosition.y = y;
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }
}
