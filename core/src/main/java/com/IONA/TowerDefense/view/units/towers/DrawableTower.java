package com.IONA.TowerDefense.view.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.view.Drawable;
import com.IONA.TowerDefense.view.ui.buttons.TowerBasicIconDrawer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public sealed interface DrawableTower extends Drawable permits TowerBasicDrawer, TowerMissileDrawer, TowerPulseDrawer {
    void drawPendingTower(SpriteBatch batch);

    void drawRange(SpriteBatch batch);
}
