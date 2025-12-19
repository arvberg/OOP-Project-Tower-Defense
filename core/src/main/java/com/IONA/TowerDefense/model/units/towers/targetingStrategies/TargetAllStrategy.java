package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.List;
/**
 * TargetAllStrategy selects all enemies within the tower's attack range.
 * <p>
 * This targeting strategy does not filter or prioritize enemies; every
 * enemy in range is returned as a valid target for the tower.
 */
public class TargetAllStrategy implements TargetingStrategy {

    private String name = "All";

    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        return enemies;
    }

    @Override
    public String getStrategy() {
        return name;
    }
}
