package com.IONA.TowerDefense.view.units.decorations;

import com.IONA.TowerDefense.model.units.decorations.Core;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;

public final class DrawableDecorationFactory {

    private DrawableDecorationFactory(){}

    public static DrawableDecoration create(Decoration decoration){
        return switch (decoration){
            case Core d -> new CoreDrawer(d);
            //case EnemyFast e   -> new EnemyFastDrawer(e);
            //case EnemyTanky e   -> new EnemyTankDrawer(e);
            default -> throw new IllegalStateException("Unexpected value: " + decoration);
        };
    }
}
