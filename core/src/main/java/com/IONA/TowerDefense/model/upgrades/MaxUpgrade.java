package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;

public class MaxUpgrade implements TowerUpgrade {

    private int cost;
    private float upgradeScaling;

    public MaxUpgrade(int cost, float upgradeScaling) {
        this.cost = cost;
        this.upgradeScaling = upgradeScaling;
    }

    @Override
    public void incrementCost() {
        this.cost = (int) (cost * upgradeScaling);
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
