package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.view.Drawable;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
/**
 * Represents a drawable enemy in the game.
 *
 * Provides a unified interface for rendering different types of enemies and their health bars.
 * All enemy drawers (EnemyBasicDrawer, EnemyFastDrawer, EnemyTankyDrawer) implement this interface.
 */
public sealed interface DrawableEnemy extends Drawable permits EnemyBasicDrawer,
    EnemyFastDrawer, EnemyTankyDrawer {
    void drawHealthBar(ShapeRenderer renderer, float delta);
}
