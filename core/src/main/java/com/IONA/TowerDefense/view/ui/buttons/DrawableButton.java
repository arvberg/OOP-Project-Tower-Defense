package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.view.Drawable;

public sealed interface DrawableButton extends Drawable permits PauseButtonDrawer, PlayButtonDrawer,
    RestartButtonDrawer, SellButtonDrawer, SpeedUpButtonDrawer, TowerMenuToggleButtonDrawer,
    UpgradeMenuToggleButtonDrawer, SideMenuToggleButtonDrawer, TowerMenuItemButtonDrawer,
    UpgradeMenuItemButtonDrawer

{
}
