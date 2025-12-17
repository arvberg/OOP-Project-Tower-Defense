package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.view.Drawable;
import com.IONA.TowerDefense.view.ui.menues.TowerMenuDrawer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public sealed interface DrawableMenu extends Drawable permits TowerMenuDrawer,
    InfoMenuDrawer, UpgradeMenuDrawer
{
    default void drawCancelTower(SpriteBatch batch) {
    }
}
