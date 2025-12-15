package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.models.AttackHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.models.TowerHandler;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TargetingStrategyToggleButton extends Button {

    TowerHandler towerHandler;

    public TargetingStrategyToggleButton(float x, float y, GameModel model) {
        super(x, y, 1, 1);
        this.width = 1f;
        this.height = 1f;
        this.bounds = new Rectangle(x, y, width, height);
        this.towerHandler = model.getTowerHandler();

    }

    @Override
    public void isClicked(Vector2 pos) {
        if (bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
        if (towerHandler.isTowerSelected()) {
            System.out.println("button clicked");
            towerHandler.toggleTargetingStrategy();
        }
    }
}
