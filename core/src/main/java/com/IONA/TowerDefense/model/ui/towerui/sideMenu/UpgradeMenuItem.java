package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;

import java.util.Deque;

public class UpgradeMenuItem extends Button {

    private final Deque<TowerUpgrade> upgrades;

    public UpgradeMenuItem(float x, float y, Deque<TowerUpgrade> upgrades, UpgradeMenu menu) {
        super(x, y, menu.getWidth() - 0.4f, 0.4f, GameAction.UPGRADE_TOWER);
        this.upgrades = upgrades;
    }

    public TowerUpgrade getNextUpgrade() {
        return upgrades.peek();
    }

    public TowerUpgrade getUpgrade() {
        TowerUpgrade nextUpgrade = upgrades.size() > 1 ? upgrades.pop() : upgrades.peek();
        assert nextUpgrade != null;
        return nextUpgrade;
    }

    public void updateNextCost(){
        TowerUpgrade nextUpgrade = upgrades.size() > 1 ? upgrades.pop() : upgrades.peek();
        nextUpgrade.incrementCost();
    }
}
