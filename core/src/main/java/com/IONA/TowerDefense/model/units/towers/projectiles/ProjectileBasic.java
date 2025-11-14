package com.IONA.TowerDefense.model.units.towers.projectiles;

import javax.swing.*;
import java.awt.*;


public class ProjectileBasic extends Projectile{
    private int projectileDirection;

    public ProjectileBasic(int attack, int speed, Point direction, Point position, Point target, ImageIcon image,GameModel model) {
        super(attack, speed, direction, position, target, image, model);
    }

    public int getProjectileDirection() {
        return projectileDirection;
    }

}
