package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;

public class MaxUpgrade implements TowerUpgrade{

    private final int cost;

    public MaxUpgrade(int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return "MaxUpgrade";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean canApply(Tower tower) {
        // ändra något kriterie senare
        return false;
    }

    @Override
    public void apply(Tower tower) {
        return;
    }
}
