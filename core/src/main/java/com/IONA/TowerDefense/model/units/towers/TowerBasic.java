package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;

import java.awt.*;

public class TowerBasic extends Tower {
    private static final int BaseDamage = 50;
    private static final int BaseProjectileSpeed = 50;
    private static final int BaseCost = 50;
    private static final int range = 100;
    private static final int BaseFireFrequency = 0;

    public TowerBasic() {
        super(BaseDamage, BaseProjectileSpeed, BaseCost, range, BaseFireFrequency);
        this.damage = BaseDamage;
        this.projectileSpeed = BaseProjectileSpeed;
        this.fireFrequency = BaseFireFrequency;
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
