package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.ArrayList;
import java.util.List;

public class TargetStrongestStrategy implements TargetingStrategy {
    String name = "Strongest";
    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        List<Enemy> result = new ArrayList<>();
        if (enemies.isEmpty()) {
            return result;
        }
        Enemy strongestEnemy = enemies.get(0);
        for (Enemy enemy :  enemies) {
            float hp = enemy.getHp();
            if (hp < strongestEnemy.getHp()) {
                strongestEnemy = enemy;
            }
        }
        result.add(strongestEnemy);
        return result;
    }
    @Override
    public String getStrategy(){
        return name;
    }
}
