package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.ArrayList;
import java.util.List;

public class TargetNearestStrategy implements TargetingStrategy {
    String name = "Nearest";
    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        List<Enemy> result = new ArrayList<>();
        if (enemies.isEmpty()) {
            return result;
        }
        Enemy closestEnemy = enemies.get(0);
        float closestDistance = VectorUtils.distance(tower.getPosition(), closestEnemy.getPosition());
        for (Enemy enemy :  enemies) {
            float distance = VectorUtils.distance(tower.getPosition(), enemy.getPosition());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestEnemy = enemy;
            }
        }
        result.add(closestEnemy);
        return result;
    }
    @Override
    public String getStrategy(){
        return name;
    }

}
