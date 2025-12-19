package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.input.GameAction;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Button {

    protected final Rectangle bounds;
    protected final Vector2 position;
    protected final float width;
    protected final float height;
    private final GameAction action;
    private boolean visible = true;

    protected Button(float x, float y, float width, float height, GameAction action) {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
        this.action = action;
    }

    public boolean isClicked(Vector2 pos) {
        return visible && bounds.contains(pos);
    }

    public GameAction getAction() {
        return action;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        bounds.setPosition(x, y);
    }

    public Vector2 getButtonPosition() {
        return position;
    }

    public boolean contains(float x, float y) {
        return visible && bounds.contains(x, y);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setButtonPosition(float x, float y) {
        position.set(x, y);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }
}
