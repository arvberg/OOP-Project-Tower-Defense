package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.Main;

import java.awt.*;

public class TowerBasic extends Tower{
    private static final int BaseAttack = 50;
    private static final int BaseSpeed = 50;
    private static final int BaseCost = 50;
    private static final int range = 100;
    private static final int BaseProjectileSpeed = 0;

    public TowerBasic(Point position, Dimension size) {
        super(BaseAttack, BaseSpeed, BaseCost, range, position, size);
        this.attack = BaseAttack;
        this.speed = BaseSpeed;
        this.cost = BaseCost;
        this.level = 1;
        this.rangeRadius = 100;
    }

    @Override
    public void attack(Targetable target, long currentTimeMillis) {
    }

    @Override
    public void fire() {
        Point tempPoint = new Point();
        tempPoint.setLocation(this.position.x, this.position.y);

        Main.addProjectile(
            new ProjectileBasic(attack, BaseProjectileSpeed, direction, tempPoint, image)
        );
    }
}
