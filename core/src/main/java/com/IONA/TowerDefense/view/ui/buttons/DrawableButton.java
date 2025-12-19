package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.view.Drawable;

public sealed interface DrawableButton extends Drawable permits
    ExitButtonDrawer,
    FireRateUpgradeDrawer,
    PauseButtonDrawer,
    PlayButtonDrawer,
    RangeUpgradeDrawer,
    DamageUpgradeDrawer,
    RestartButtonDrawer,
    SellButtonDrawer,
    SpeedUpButtonDrawer,
    TargetingToggleButtonDrawer,
    TowerBasicIconDrawer,
    TowerMenuItemButtonDrawer,
    TowerMissileIconDrawer,
    TowerPulseIconDrawer,
    UpgradeMenuItemButtonDrawer{
}
