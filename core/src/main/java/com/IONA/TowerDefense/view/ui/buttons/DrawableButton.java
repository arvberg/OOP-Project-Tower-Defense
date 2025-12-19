package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.view.Drawable;
/**
 * Marker interface for all button types that can be drawn in the UI.
 * <p>
 * Only the specified classes are permitted to implement this interface,
 * ensuring type safety and consistent rendering behavior for UI buttons.
 */
public sealed interface DrawableButton extends Drawable permits
    ExitButtonDrawer,
    FireRateUpgradeDrawer,
    PauseButtonDrawer,
    PlayButtonDrawer,
    RangeUpgradeDrawer,
    RestartButtonDrawer,
    SellButtonDrawer,
    SpeedUpButtonDrawer,
    TargetingToggleButtonDrawer,
    TowerBasicIconDrawer,
    TowerMenuItemButtonDrawer,
    TowerMissileIconDrawer,
    TowerPulseIconDrawer,
    UpgradeMenuItemButtonDrawer {
}
