package com.IONA.TowerDefense.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Drawable {
    void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta);
}
