package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.buttonui.PauseButton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class PauseButtonDrawer implements DrawableButton{
    Texture texture = new Texture("Pause_button_temp_01.png");
    Vector2 p;
    PauseButton button;
    float DimensionX;
    float DimensionY;

    public PauseButtonDrawer(PauseButton button) {
        this.DimensionX = button.getWidth();
        this.DimensionY = button.getHeight();
        this.button = button;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        p = button.getButtonPosition();
        batch.draw(texture, p.x, p.y, DimensionX, DimensionY);
    }
}
