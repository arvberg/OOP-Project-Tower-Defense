package com.IONA.TowerDefense.view.units.projectiles;

import com.IONA.TowerDefense.view.Drawable;
/**
 * Marker interface for drawable projectiles.
 *
 * Represents the visual layer of any projectile in the game.
 * Implemented by specific projectile drawers such as ProjectileBasicDrawer and ProjectileMissileDrawer.
 */
public sealed interface DrawableProjectile extends Drawable permits ProjectileBasicDrawer, ProjectileMissileDrawer {
}
