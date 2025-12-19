package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;

public class DamageUpgrade implements TowerUpgrade {


    private int cost;

    public DamageUpgrade(int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return "DamageUpgrade";
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
    public void incrementCost(float f){
        this.cost = (int)(cost*f);
    };

    @Override
    public void apply(Tower tower) {
        float currentDamage = tower.getDamage();
        float maxDamage = tower.getBaseDamage() * 0.5f;
        float factor = 0.2f; // 20% av skillnaden i ökning

        float newRate = currentDamage - (currentDamage - maxDamage) * factor;
        tower.setFireRate(newRate);
        System.out.println("new rate: " + currentDamage);
    }
}
