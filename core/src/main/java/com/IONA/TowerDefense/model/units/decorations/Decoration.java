package com.IONA.TowerDefense.model.units.decorations;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Represents a static decoration or object in the game world.
 * <p>
 * Decorations are a type of Unit but usually non-interactive and do not move.
 * Each decoration has a position, dimensions, texture, and hitbox for collisions.
 * The hitbox is automatically centered on the decoration.
 */
public class Decoration extends Unit {
    public static boolean TEST_MODE = false;
    protected Vector2 position = new Vector2();
    public Texture texture;
    public float width;
    public float height;
    public Rectangle hitBox;

    public Decoration() {
        if (!TEST_MODE) {
            this.texture = new Texture(Assets.ENEMY_BASIC_BACK);
        } else {
            this.texture = null;
        }
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
        setHitBox(width, height);
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setHitBox(float width, float height) {


        float newX = (position.x - width / 2);
        float newY = (position.y - height / 2);


        if (hitBox == null) {
            hitBox = new Rectangle(newX, newY, width, height);
        } else {
            hitBox.set(newX, newY, width, height);
        }
    }
}
