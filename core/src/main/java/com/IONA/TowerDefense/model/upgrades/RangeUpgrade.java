package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;
import org.w3c.dom.ranges.Range;

public class RangeUpgrade implements TowerUpgrade{

    private int cost;

    public RangeUpgrade(int cost) {
        this.cost = cost;
    }

    @Override
    public void incrementCost(float f){
        this.cost = (int)(cost*f);
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
