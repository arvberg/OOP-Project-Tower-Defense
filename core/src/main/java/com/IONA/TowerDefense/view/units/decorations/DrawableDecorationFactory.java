package com.IONA.TowerDefense.view.units.decorations;

import com.IONA.TowerDefense.model.units.decorations.Core;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;
/**
 * Factory class for creating drawable representations of decorations.
 *
 * Converts a given Decoration instance into its corresponding DrawableDecoration implementation.
 * Currently supports Core decorations. Throws an exception for unknown decoration types.
 */
public final class DrawableDecorationFactory {

    private DrawableDecorationFactory() {
    }

    public static DrawableDecoration create(Decoration decoration) {
        return switch (decoration) {
            case Core d -> new CoreDrawer(d);
            default -> throw new IllegalStateException("Unexpected value: " + decoration);
        };
    }
}
