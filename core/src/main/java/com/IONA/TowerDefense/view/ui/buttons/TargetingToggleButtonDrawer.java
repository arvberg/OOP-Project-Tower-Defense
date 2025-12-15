package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.buttonui.SpeedUpButton;
import com.IONA.TowerDefense.model.ui.buttonui.TargetingStrategyToggleButton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class TargetingToggleButtonDrawer implements DrawableButton {
    private final TargetingStrategyToggleButton targetingToggleButton;
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;

    // STATIC TEXTURE
    private static final Texture TEXTURE = new Texture("SpeedButton_temp_01.png");

    public TargetingToggleButtonDrawer(TargetingStrategyToggleButton target) {
        this.targetingToggleButton = target;
        this.dimensionX = targetingToggleButton.getWidth();
        this.dimensionY = targetingToggleButton.getHeight();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        p = targetingToggleButton.getButtonPosition();
        batch.draw(TEXTURE, p.x, p.y, dimensionX, dimensionY);
    }

    public static void disposeStatic() {
        if (TEXTURE != null) TEXTURE.dispose();
    }
}

