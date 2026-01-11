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
    private float upgradeScaling;

    public DamageUpgrade(int cost, float upgradeScaling) {
        this.cost = cost;
        this.upgradeScaling = upgradeScaling;
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
    public void incrementCost() {
        this.cost = (int) (cost * upgradeScaling);
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
