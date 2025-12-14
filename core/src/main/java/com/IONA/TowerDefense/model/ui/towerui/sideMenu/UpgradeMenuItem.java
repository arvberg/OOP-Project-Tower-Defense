package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Deque;

public class UpgradeMenuItem extends Button {

    private final GameModel model;
    private TowerUpgrade nextUpgrade;
    private Deque<TowerUpgrade> upgrades;
    private static final float width = 0.6f;
    private static final float height = 0.6f;
    private static final float xOrigin = width/2;
    private static final float yOrigin = height/2;

    public UpgradeMenuItem(float x, float y, GameModel model, Deque<TowerUpgrade> upgrades) {
        super(x-xOrigin,y-yOrigin, width, height);  // 1x1 world units
        this.model = model;
        this.upgrades = upgrades;
        this.nextUpgrade = upgrades.peek();
        this.bounds = new Rectangle(x-xOrigin, y-yOrigin, width, height);

    }

    @Override
    public void isClicked(Vector2 pos) {
        if(bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
        if (model.isTowerSelected() && !upgrades.isEmpty()) {
            nextUpgrade = upgrades.pop();
            model.upgradeTower(model.getSelectedTower(), nextUpgrade);
            
            System.out.println("Upgraded");
        }
    }


}
