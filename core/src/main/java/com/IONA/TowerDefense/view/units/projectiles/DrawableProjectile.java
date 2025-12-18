package com.IONA.TowerDefense.view.units.projectiles;

import com.IONA.TowerDefense.view.Drawable;

public sealed interface DrawableProjectile extends Drawable permits ProjectileBasicDrawer, ProjectileMissileDrawer {
}
