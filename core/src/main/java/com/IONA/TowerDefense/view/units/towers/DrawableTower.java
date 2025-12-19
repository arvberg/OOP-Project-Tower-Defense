package com.IONA.TowerDefense.view.units.towers;

import com.IONA.TowerDefense.view.Drawable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * Represents a drawable tower in the game.
 *
 * Provides methods for rendering the tower itself, its attack range, and
 * any pending placement visuals. Implemented by specific tower drawer classes.
 */
public sealed interface DrawableTower extends Drawable permits TowerBasicDrawer, TowerMissileDrawer, TowerPulseDrawer {
    void drawPendingTower(SpriteBatch batch);

    void drawRange(SpriteBatch batch);
}
