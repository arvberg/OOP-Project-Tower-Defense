package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;

import java.util.Deque;

public class UpgradeMenuItem extends Button {

    private final Deque<TowerUpgrade> upgrades;

    public UpgradeMenuItem(float x, float y, Deque<TowerUpgrade> upgrades) {
        super(x - .3f, y - .3f, .6f, .6f, GameAction.UPGRADE_TOWER);
        this.upgrades = upgrades;
    }

    public TowerUpgrade getNextUpgrade() {
        return upgrades.peek();
    }

    public TowerUpgrade consumeUpgrade() {
        return upgrades.size() > 1 ? upgrades.pop() : upgrades.peek();
    }
}
