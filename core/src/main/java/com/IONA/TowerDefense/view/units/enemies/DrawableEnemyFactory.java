package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.model.units.enemies.EnemyFast;
import com.IONA.TowerDefense.model.units.enemies.EnemyTanky;

public final class DrawableEnemyFactory {

    private DrawableEnemyFactory(){}

    public static DrawableEnemy create(Enemy enemy){
        return switch (enemy){
            case EnemyBasic e -> new EnemyBasicDrawer(e);
            case EnemyFast e   -> new EnemyFastDrawer(e);
            //case EnemyTanky e   -> new EnemyTankDrawer(e);
            default -> throw new IllegalStateException("Unexpected value: " + enemy);
        };
    }
}
