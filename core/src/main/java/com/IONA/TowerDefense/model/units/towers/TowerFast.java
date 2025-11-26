package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;

import java.awt.*;

public class TowerFast extends Tower{
    private static final int BaseAttack = 25;
    private static final int BaseSpeed = 100;
    private static final int BaseCost = 50;
    private static final int range = 100;
    private static final int BaseProjectileSpeed = 0;

    private static final long BaseFireRate = 1000;


    public TowerFast() {
        super(BaseSpeed, BaseCost, range, BaseFireRate);
        setDamage(damage);
        this.projectileSpeed = BaseSpeed;
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
        tempPoint.setLocation(this.getPosition().x, this.getPosition().y);
    }
}
