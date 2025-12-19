package com.IONA.TowerDefense.view.units.towers;


import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.TowerMissile;
import com.IONA.TowerDefense.model.units.towers.TowerPulse;
import com.IONA.TowerDefense.view.ui.buttons.DrawableButton;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for creating drawable representations of towers.
 *
 * Converts a given Tower instance into its corresponding DrawableTower implementation.
 * Caches created views to ensure each tower has a single drawable instance.
 */
public final class DrawableTowerFactory {

    private static final Map<Tower, DrawableTower> existingViews = new HashMap<>();

    private DrawableTowerFactory() {
    }

    public static DrawableTower create(Tower tower) {
        if (existingViews.containsKey(tower)) {
            return existingViews.get(tower);
        }
        DrawableTower view = switch (tower) {
            case TowerBasic t -> new TowerBasicDrawer(t);
            case TowerPulse t -> new TowerPulseDrawer(t);
            case TowerMissile t -> new TowerMissileDrawer(t);
            default -> throw new IllegalStateException("Unexpected value: " + tower);
        };
        existingViews.put(tower, view);
        return view;
    }

}
