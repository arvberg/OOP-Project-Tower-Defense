package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.units.towers.projectiles.ProjectileBasic;
import com.IONA.TowerDefense.model.Targetable;

import java.awt.*;

public class TowerFast extends Tower{
    private static final int BaseAttack = 25;
    private static final int BaseSpeed = 100;
    private static final int BaseCost = 50;
    private static final int range = 100;
    private static final int BaseProjectileSpeed = 0;

    private GameModel model;

    public TowerFast(GameModel model) {
        super(BaseAttack, BaseSpeed, BaseCost, range, model);
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

        model.addProjectile(
            new ProjectileBasic(attack, BaseProjectileSpeed, direction, tempPoint, image)
        );
    }
}
