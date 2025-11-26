package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.List;

public interface TargetingStrategy {
    List<Enemy> pick(List<Enemy> enemies, Tower tower);
}
