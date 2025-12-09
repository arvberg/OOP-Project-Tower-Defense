package com.IONA.TowerDefense.model.units.towers.attackStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public interface AttackStrategy {
    void attack(Tower tower, List<Enemy> enemies);
}
