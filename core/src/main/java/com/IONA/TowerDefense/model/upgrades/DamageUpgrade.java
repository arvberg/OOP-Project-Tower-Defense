package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;
/**
 * DamageUpgrade represents an upgrade that increases a Tower's damage or attack efficiency.
 *
 * This upgrade can be applied to any Tower, and its cost can scale over time.
 * When applied, it modifies the Tower's firing properties based on a factor
 * of the difference between the current and base damage.
 */
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
    public void incrementCost(float f) {
        this.cost = (int) (cost * f);
    }

    ;

    @Override
    public void apply(Tower tower) {
        float currentDamage = tower.getDamage();
        float maxDamage = tower.getBaseDamage() * 2f;
        float factor = 0.2f; // 20% av skillnaden i ökning

        int newRate = (int)(currentDamage + (maxDamage - currentDamage) * factor);
        tower.setDamage(newRate);
        System.out.println("new rate: " + currentDamage);
    }
}
