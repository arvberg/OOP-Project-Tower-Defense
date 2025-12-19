package com.IONA.TowerDefense.view.units.enemies;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.view.Drawable;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public sealed interface DrawableEnemy extends Drawable permits EnemyBasicDrawer,
    EnemyFastDrawer, EnemyTankyDrawer {
    void drawHealthBar(ShapeRenderer renderer, float delta);
}
