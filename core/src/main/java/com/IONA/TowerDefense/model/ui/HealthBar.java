package com.IONA.TowerDefense.model.ui;

import com.badlogic.gdx.math.Vector2;
/**
 * Represents a health bar UI element in the game.
 * <p>
 * A HealthBar tracks the current health of an entity and knows its
 * position and size for rendering purposes. It does not handle
 * drawing itself; it is just a data container for health information.
 */
public class HealthBar {
    protected int currentHealth;
    protected Vector2 position;
    public float width;
    public float height;

    public HealthBar(int currentHealth, Vector2 position, float width, float height) {
        this.currentHealth = currentHealth;
        this.position = new Vector2(position);
        this.width = width;
        this.height = height;

    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }
}
