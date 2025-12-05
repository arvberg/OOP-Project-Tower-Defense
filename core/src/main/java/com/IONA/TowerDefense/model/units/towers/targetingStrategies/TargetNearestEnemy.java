package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.ArrayList;
import java.util.List;

public class TargetNearestEnemy implements TargetingStrategy {

    private float getDistance(Tower tower, Enemy enemy) {
        float dx = enemy.getX() - tower.getX();
        float dy = enemy.getY() - tower.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        List<Enemy> result = new ArrayList<>();

        if (enemies == null || enemies.isEmpty()) {
            return result;
        }
        Enemy closest = enemies.get(0);
        float closestDist = getDistance(tower, closest);

        for (int i = 1; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            float dist = getDistance(tower, enemy);

            if (dist < closestDist) {
                closest = enemy;
                closestDist = dist;
            }
        }
        result.add(closest);
        return result;
    }
}
