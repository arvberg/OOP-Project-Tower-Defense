package com.IONA.TowerDefense.view.units.decorations;

import com.IONA.TowerDefense.view.Drawable;
/**
 * Marker interface for drawable decorations in the game.
 *
 * Any class implementing this interface represents a visual, renderable decoration.
 */
public sealed interface DrawableDecoration extends Drawable permits CoreDrawer {

}
