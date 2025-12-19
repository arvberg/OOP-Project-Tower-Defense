package com.IONA.TowerDefense.model.units;

import com.badlogic.gdx.math.Vector2;
/**
 * Unit is the base class for all game entities that have a position in the world.
 * <p>
 * It provides basic position tracking using a 2D vector, along with
 * convenience methods to get and set the X and Y coordinates individually.
 * <p>
 * This class is abstract and intended to be extended by specific types of units,
 * such as Towers, Enemies, or Decorations.
 */
public abstract class Unit {
    protected Vector2 position = new Vector2();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}
