package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;

public class SpeedUpgrade implements TowerUpgrade {

    private final int cost;

    public SpeedUpgrade (int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return "SpeedUpgrade";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean canApply(Tower tower) {
        return true;
    }

    @Override
    public void apply(Tower tower) {
        float fireRate = tower.getFireRate();
        float maxRate = fireRate * 2;
        float factor = 0.2f; // 20% av skillnaden i ökning

        float newRate = fireRate + ((maxRate - fireRate) * factor);
        tower.setFireRate(newRate);
    }
}
