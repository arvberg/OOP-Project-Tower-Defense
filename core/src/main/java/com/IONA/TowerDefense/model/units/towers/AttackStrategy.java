package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;

import java.util.List;

public interface AttackStrategy {
    void attack(Tower tower, List<Enemy> enemies, List<Projectile> projectilesOut, float deltaTime);
}
