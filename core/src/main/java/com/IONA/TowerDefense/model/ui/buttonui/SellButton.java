package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.models.GameModel;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public class SellButton extends Button {

    public SellButton (float x, float y, GameModel model) {
        super( x, y, 1, 1);
        this.width = 1f;
        this.height = 1f;
        this.bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public void onClick() {
        if (model.isTowerSelected()) {
            model.sellTower(model.getSelectedTower());
            model.deselectTower();
        }
    }
}
