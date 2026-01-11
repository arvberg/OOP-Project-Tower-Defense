package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;

public interface TowerUpgrade {
    String getName();

    int getCost();

    boolean canApply(Tower tower);

    void apply(Tower tower);

    void incrementCost();
}
