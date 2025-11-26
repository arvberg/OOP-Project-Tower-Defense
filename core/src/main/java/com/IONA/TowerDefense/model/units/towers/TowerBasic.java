package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingEnemyStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TowerBasic extends Tower {
    private static final int BaseDamage = 50;
    private static final int BaseSpeed = 8;
    private static final int BaseCost = 50;
    private static final float BaseFireRate = 0.1F;
    private static final int range = 1;
    private float cooldown = 0f;

    public Texture texture = new Texture("Tower_temp_03.png");

    public TowerBasic() {
        super(BaseSpeed, BaseCost, range, BaseFireRate);

        setDamage(BaseDamage);
        this.projectileSpeed = BaseSpeed;
        this.cost = BaseCost;
        this.level = 1;
        this.rangeRadius = 100;
        this.cooldown = BaseFireRate;
        this.setTargetingStrategy(new TargetLeadingEnemyStrategy());
        setAttackType("HomingProjectile");
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
