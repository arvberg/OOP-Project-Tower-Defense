package com.IONA.TowerDefense.model.ui.playerui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
/**
 * Represents a player resource in the game, such as health (HP) or money.
 * <p>
 * Each resource has a current value, a position for rendering on screen,
 * dimensions, a text representation, and a color for drawing.
 */
public class Resource {
    private int currentResource;
    protected Vector2 position;
    protected float width;
    protected float height;
    public String textBar;
    public Color color;

    public Resource(int currentResource, Vector2 position, float width, float height) {
        this.currentResource = currentResource;
        this.position = new Vector2(position);
        this.width = width;
        this.height = height;
        this.textBar = String.valueOf(this.currentResource);
    }

    public void setCurrentResource(int currentResource) {
        this.currentResource = currentResource;
    }

    public String getTextBar() {
        return textBar;
    }

    public Vector2 getPosition() {
        return position;
    }
}
