package com.IONA.TowerDefense.view.units.towers;

import com.IONA.TowerDefense.view.Drawable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public sealed interface DrawableTower extends Drawable permits TowerBasicDrawer{
    void drawPendingTower(SpriteBatch batch);
    void drawRange(SpriteBatch batch);
}
