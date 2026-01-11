package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;
import org.w3c.dom.ranges.Range;
/**
 * RangeUpgrade represents an upgrade that increases a Tower's attack range.
 *
 * This upgrade can be applied to any Tower. When applied, it increases the Tower's range
 * by a fraction of the difference between the current range and a calculated maximum range,
 * allowing the tower to reach enemies farther away.
 *
 * The cost of the upgrade can be incremented over time using the incrementCost() method.
 */
public class RangeUpgrade implements TowerUpgrade {

    private int cost;
    private float upgradeScaling;

    public RangeUpgrade(int cost, float upgradeScaling) {
        this.cost = cost;
        this.upgradeScaling = upgradeScaling;
    }

    @Override
    public void incrementCost() {
        this.cost = (int) (cost * upgradeScaling);
    }

    @Override
    public String getName() {
        return "RangeUpgrade";
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public boolean canApply(Tower tower) {
        // ändra något kriterie senare
        return true;
    }

    @Override
    public void apply(Tower tower) {
        float currentRange = tower.getRange();
        float maxRange = tower.getBaseRange() * 2;
        float factor = 0.2f;

        float newRange = currentRange + ((maxRange - currentRange) * factor);
        tower.setRange(newRange);
    }
}
