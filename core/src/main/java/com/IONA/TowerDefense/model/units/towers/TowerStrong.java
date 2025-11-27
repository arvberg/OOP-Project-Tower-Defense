package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TowerStrong extends Tower {
    private static final int BaseDamage = 500;
    private static final int BaseSpeed = 1;
    private static final int BaseCost = 50;
    private static final float BaseFireRate = 0.01F;
    private static final int range = 1;
    private float cooldown = 0f;

    public Texture texture = new Texture("Tower_temp_04.png");

    public TowerStrong() {
        super(BaseSpeed, BaseCost, range, BaseFireRate);

        setDamage(BaseDamage);
        this.projectileSpeed = BaseSpeed;
        this.cost = BaseCost;
        this.level = 1;
        this.rangeRadius = 100;
        this.cooldown = BaseFireRate;
        this.setTargetingStrategy(new TargetLeadingStrategy());
        setAttackType("Bomb");
    }

    @Override
    public List<Enemy> getTargets(List<Enemy> enemies) {
        return targetingStrategy.pick(enemies, this);
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
        Vector2 tempPoint = new Vector2(this.getPosition().x, this.getPosition().y);
    }
}
