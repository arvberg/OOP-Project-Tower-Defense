package com.IONA.TowerDefense.view.units.towers;

import com.IONA.TowerDefense.view.Drawable;
import com.IONA.TowerDefense.view.ui.buttons.TowerBasicIconDrawer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public sealed interface DrawableTower extends Drawable permits TowerBasicDrawer, TowerPulseDrawer {
    void drawPendingTower(SpriteBatch batch);
    void drawRange(SpriteBatch batch);
}
