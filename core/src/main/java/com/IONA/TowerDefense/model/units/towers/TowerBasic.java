package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class TowerBasic extends Tower {
    private static final int BaseAttack = 50;
    private static final float BaseSpeed = 8f;
    private static final int BaseCost = 50;
    private static final float BaseFireRate = 1;
    private static final float range = 3f;

    private float cooldown = 0f;

    public Texture texture = new Texture("Tower_temp_03.png");


    public TowerBasic() {
        super(BaseAttack, BaseSpeed, BaseCost, range, BaseFireRate);
        this.attack = BaseAttack;
        this.projectileSpeed = BaseSpeed;
        this.cost = BaseCost;
        this.level = 1;
        this.rangeRadius = 100;
        this.cooldown = BaseFireRate;
    }

    @Override
    public boolean canShoot() {
        return cooldown <= 0f;
    }

    @Override
    public void resetCooldown() {
        cooldown = fireRate;
    }
    @Override
    public void update(){
        cooldown -= HeartBeat.delta;
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
