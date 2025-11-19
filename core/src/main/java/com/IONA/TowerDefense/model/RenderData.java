package com.IONA.TowerDefense.model;

import com.badlogic.gdx.graphics.Texture;


public class RenderData {
    private Texture texture;
    private float x, y, width, height;

    public RenderData(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
