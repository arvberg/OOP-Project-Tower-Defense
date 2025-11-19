package com.IONA.TowerDefense.model.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class RenderData {
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    public RenderData (Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
