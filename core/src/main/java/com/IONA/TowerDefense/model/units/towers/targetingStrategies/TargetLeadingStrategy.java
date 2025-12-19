package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * TargetAllStrategy selects all enemies within the tower's attack range.
 * <p>
 * This targeting strategy does not filter or prioritize enemies; every
 * enemy in range is returned as a valid target for the tower.
 */
public class TargetLeadingStrategy implements TargetingStrategy {
    String name = "Leading";

    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        List<Enemy> result = new ArrayList<>();
        if (enemies.isEmpty()) {
            return result;
        }
        Enemy leading = enemies.get(0);
        result.add(leading);
        return result;
    }

    @Override
    public String getStrategy() {
        return name;
    }
}
