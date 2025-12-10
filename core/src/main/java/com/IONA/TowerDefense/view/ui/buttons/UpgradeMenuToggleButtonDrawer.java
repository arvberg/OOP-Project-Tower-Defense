package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuToggleButton;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuToggleButton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class UpgradeMenuToggleButtonDrawer implements DrawableButton{
    Texture texture = new Texture("Upgrade_button_temp.png");
    Vector2 p;
    UpgradeMenuToggleButton button;
    float DimensionX;
    float DimensionY;

    public UpgradeMenuToggleButtonDrawer(UpgradeMenuToggleButton button) {
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
