package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.view.Drawable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * Marker interface for all drawable menu components in the game UI.
 * <p>
 * Implementations of DrawableMenu represent specific menus (e.g., TowerMenu, InfoMenu,
 * Upgrade Menus) that can be rendered. Provides a default method for drawing a
 * cancel tower indicator, which can be overridden by specific menu implementations.
 */
public sealed interface DrawableMenu extends Drawable permits TowerMenuDrawer,
    InfoMenuDrawer, UpgradeMenuTowerBasicDrawer, UpgradeMenuTowerMissileDrawer,
    UpgradeMenuTowerPulseDrawer {
    default void drawCancelTower(SpriteBatch batch) {
    }
}
