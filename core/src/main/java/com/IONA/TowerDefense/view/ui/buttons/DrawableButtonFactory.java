package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.controller.buttonui.*;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;
import com.IONA.TowerDefense.view.units.towers.DrawableTower;

import java.util.HashMap;
import java.util.Map;
/**
 * Factory class for creating drawable UI button representations.
 * <p>
 * Maintains a cache of existing DrawableButton instances to ensure that each
 * logical Button object is associated with a single DrawableButton view.
 * Uses a switch expression to instantiate the correct drawable type based
 * on the specific Button subclass.
 */
public class DrawableButtonFactory {

    private static final Map<Button, DrawableButton> existingViews = new HashMap<>();

    private DrawableButtonFactory() {
    }

    public static DrawableButton create(Button button) {
        if (existingViews.containsKey(button)) {
            return existingViews.get(button);
        }
        DrawableButton view = switch (button) {
            case PauseButton b -> new PauseButtonDrawer(b);
            case PlayButton b -> new PlayButtonDrawer(b);
            case RestartButton b -> new RestartButtonDrawer(b);
            case SellButton b -> new SellButtonDrawer(b);
            case SpeedUpButton b -> new SpeedUpButtonDrawer(b);
            case TowerMenuItem b -> DrawableTowerIconFactory.create(b);
            case UpgradeMenuItem b -> DrawableUpgradeFactory.create(b);
            case ExitButton b -> new ExitButtonDrawer(b);
            case TargetingStrategyToggleButton b -> new TargetingToggleButtonDrawer(b);
            default -> throw new IllegalStateException("Unexpected value: " + button);
        };
        existingViews.put(button, view);
        return view;
    }
}
