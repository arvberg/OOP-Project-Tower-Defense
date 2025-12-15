package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.models.GameModel;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public class SellButton extends Button {

    private static final float width = 0.6f;
    private static final float height = 0.6f;
    private static final float xOrigin = width/2;
    private static final float yOrigin = height/2;

    public SellButton (float x, float y, GameModel model) {
        super(x-xOrigin, y-yOrigin, width, height);
        this.bounds = new Rectangle(x-xOrigin, y-yOrigin, width, height);
    }

    @Override
    public void isClicked(Vector2 pos) {
        if (bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
        if (model.isTowerSelected()) {
            model.sellTower(model.getSelectedTower());
        }
    }
}
