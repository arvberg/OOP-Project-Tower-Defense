package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.List;

public interface AttackStrategy {
    void attack(Tower tower, List<Enemy> enemies, List<Projectile> projectilesOut, float deltaTime);
}
