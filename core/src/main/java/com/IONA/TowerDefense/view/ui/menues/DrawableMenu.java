package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.view.Drawable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public sealed interface DrawableMenu extends Drawable permits TowerMenuDrawer,
    InfoMenuDrawer, UpgradeMenuTowerBasicDrawer, UpgradeMenuTowerMissileDrawer,
    UpgradeMenuTowerPulseDrawer {
    default void drawCancelTower(SpriteBatch batch) {
    }
}
