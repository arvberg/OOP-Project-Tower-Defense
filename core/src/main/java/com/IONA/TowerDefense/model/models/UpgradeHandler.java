package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;

public class UpgradeHandler {

    private final GameModel model;

    public UpgradeHandler(GameModel model) {
        this.model = model;
    }

    /**
     * Försöker applicera en upgrade på ett torn.
     */
    public void upgrade(Tower tower, TowerUpgrade upgrade) {
            upgrade.apply(tower);
    }
}
