package com.IONA.TowerDefense.model.upgrades;

import com.IONA.TowerDefense.model.units.towers.Tower;
import org.w3c.dom.ranges.Range;

public class RangeUpgrade implements TowerUpgrade{

    private final int cost;

    public RangeUpgrade(int cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return "RangeUpgrade";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean canApply(Tower tower) {
        // ändra något kriterie senare
        return true;
    }

    @Override
    public void apply(Tower tower) {
        float range = tower.getRange();
        float Rmax = range * 2;
        float factor = 0.2f;

        float newRange = range + ((Rmax - range) * factor);
        tower.setRange(newRange);
    }
}
