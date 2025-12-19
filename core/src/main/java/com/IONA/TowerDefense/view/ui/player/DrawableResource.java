package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.view.Drawable;
/**
 * Marker interface for drawable player resources.
 *
 * Represents UI elements like money and health points that can be drawn on the screen.
 */
public sealed interface DrawableResource extends Drawable permits MoneyDrawer, HpDrawer {
}
