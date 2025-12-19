package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;
/**
 * FireRateUpgrade represents an upgrade that increases a Tower's attack speed.
 *
 * This upgrade can be applied to any Tower. When applied, it reduces the Tower's fire rate
 * (time between shots) by a fraction of the difference between the current fire rate
 * and a calculated maximum improvement rate. This results in faster attacks as upgrades are applied.
 *
 * The cost of the upgrade can be incremented over time using the incrementCost() method.
 */
public class FireRateUpgrade implements TowerUpgrade {

    private int cost;

    public FireRateUpgrade(int cost) {
        this.cost = cost;
    }

    @Override
    public void incrementCost(float f) {
        this.cost = (int) (cost * f);
    }

    @Override
    public String getName() {
        return "FireRateUpgrade";
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
        float currentRate = tower.getFireRate();
        float maxRate = tower.getBaseFireRate() * 0.5f;
        float factor = 0.2f; // 20% av skillnaden i ökning

        float newRate = currentRate - (currentRate - maxRate) * factor;
        tower.setFireRate(newRate);
        System.out.println("new rate: " + currentRate);
    }
}
