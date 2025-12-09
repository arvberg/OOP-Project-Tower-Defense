package com.IONA.TowerDefense.view.units.decorations;

import com.IONA.TowerDefense.model.units.decorations.Core;
import com.badlogic.gdx.math.Vector2;

public final class CoreDrawer implements DrawableDecoration{
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;
    private final Core core;

    public CoreDrawer(Core core){
        this.core = core;
    }
}
