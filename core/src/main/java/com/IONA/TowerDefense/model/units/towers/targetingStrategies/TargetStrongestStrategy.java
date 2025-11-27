package com.IONA.TowerDefense.model.units.towers.targetingStrategies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.Vector2.dst;

public class TargetStrongestStrategy implements TargetingStrategy {

    @Override
    public List<Enemy> pick(List<Enemy> enemies, Tower tower) {
        List<Enemy> result = new ArrayList<>();
        if (enemies.isEmpty()) {
            return result;
        }

        Enemy strongest = enemies.get(0);

        for (int i = 1; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            if (strongest.getHp() < enemy.getHp()) {
                strongest = enemy;
            }
        }

        result.add(strongest);
        return result;
    }
}
