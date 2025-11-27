package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.Vector2.dst;

public class TargetNearestStrategy implements TargetingStrategy {

    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        List<Enemy> result = new ArrayList<>();
        if (enemies.isEmpty()) {
            return result;
        }

        Enemy nearest = enemies.get(0);
        float closestDistance = dst(nearest.getX(), nearest.getY(),
            tower.getX(), tower.getY());

        for (int i = 1; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            float distance = dst(enemy.getX(), enemy.getY(),
                tower.getX(), tower.getY());

            if (distance < closestDistance) {
                nearest = enemy;
                closestDistance = distance;
            }
        }

        result.add(nearest);
        return result;
    }
}

