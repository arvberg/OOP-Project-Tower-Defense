package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.buttonui.*;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;

public class DrawableButtonFactory {

    private DrawableButtonFactory(){}

    public static DrawableButton create(Button button){
        return switch (button){
            case PauseButton b -> new PauseButtonDrawer(b);
            case PlayButton b -> new PlayButtonDrawer(b);
            case RestartButton b -> new RestartButtonDrawer(b);
            case SellButton b -> new SellButtonDrawer(b);
            case SpeedUpButton b -> new SpeedUpButtonDrawer(b);
            case TowerMenuItem b -> new TowerMenuItemButtonDrawer(b);
            case UpgradeMenuItem b -> DrawableUpgradeFactory.create(b);
            case ExitButton b -> new ExitButtonDrawer(b);
            case TargetingStrategyToggleButton b -> new TargetingToggleButtonDrawer(b);
            default -> throw new IllegalStateException("Unexpected value: " + button);
        };
    }
}
