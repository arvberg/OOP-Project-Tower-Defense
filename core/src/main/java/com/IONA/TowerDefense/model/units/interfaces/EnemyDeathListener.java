package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.units.enemies.Enemy;

public interface EnemyDeathListener {
    default void onEnemyDeath(Enemy enemy) {
    }
}
