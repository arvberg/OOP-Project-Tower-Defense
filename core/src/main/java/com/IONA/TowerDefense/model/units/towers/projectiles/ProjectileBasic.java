package com.IONA.TowerDefense.model.units.towers.projectiles;

import javax.swing.*;
import java.awt.*;

public class ProjectileBasic extends Projectile{
    private int projectileDirection;

    public ProjectileBasic(int attack, int speed, int direction, Point position, ImageIcon image) {
        super(attack, speed, direction, position, image);
    }

    public int getProjectileDirection() {
        return projectileDirection;
    }
}
