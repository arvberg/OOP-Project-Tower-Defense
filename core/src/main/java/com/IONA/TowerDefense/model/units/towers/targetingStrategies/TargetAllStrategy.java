package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.List;

public class TargetAllStrategy implements TargetingStrategy {

    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        return enemies;
    }
}
