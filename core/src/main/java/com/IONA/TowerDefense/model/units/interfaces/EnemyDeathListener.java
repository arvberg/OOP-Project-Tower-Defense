package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.units.enemies.Enemy;

public interface EnemyDeathListener {
    void onEnemyDeath(Enemy enemy);
}
