package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;
import com.badlogic.gdx.math.Vector2;

public class UpgradeMenuItem extends Button {

    private final GameModel model;
    private final TowerUpgrade upgrade;

    public UpgradeMenuItem(float x, float y, GameModel model, TowerUpgrade upgrade) {
        super(x-.9f/2, y-.9f/2, .9f, .9f);  // 1x1 world units
        this.model = model;
        this.upgrade = upgrade;
    }

    @Override
    public void isClicked(Vector2 pos) {
        if(bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
        if (model.isTowerSelected()) {
            model.upgradeTower(model.getSelectedTower(), upgrade);
            System.out.println("Upgraded");
        }
    }


}
