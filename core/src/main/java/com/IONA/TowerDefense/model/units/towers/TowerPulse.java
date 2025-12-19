package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.DamageUpgrade;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.math.Vector2;
/**
 * TowerPulse is an area-of-effect (AOE) tower that attacks all enemies within its range.
 * <p>
 * This tower uses an AreaAttackStrategy to damage multiple enemies simultaneously,
 * making it ideal for crowd control rather than single-target focus.
 * It does not require rotation and can attack as soon as its cooldown is finished.
 * <p>
 * TowerPulse supports upgrades for fire rate, range, and maximum damage potential.
 * <p>
 * The tower can attack whenever its cooldown has elapsed, without any aiming requirements.
 */
public class TowerPulse extends Tower {

    public TowerPulse() {
        dimension = new Vector2(1f, 1f);
        damage = 15;
        baseDamage = 15;
        projectileSpeed = 8;
        baseFireRate = 0.1f;
        cost = 260;
        fireRate = 0.5f;
        range = 2f;
        baseRange = 2f;
        rotationSpeed = 0f;
        currentDirection = new Vector2(0, 0);
        desiredDirection = new Vector2(0, 0);
        cooldown = 0f;
        attackStrategy = new AreaAttackStrategy();
        targetingStrategies.add(new TargetAllStrategy());
        targetingStrategy = targetingStrategies.getFirst();
        upgradePath1.add(new RangeUpgrade(175));
        upgradePath2.add(new FireRateUpgrade(175));
        upgradePath3.add(new DamageUpgrade(200));
        towerType = "TowerPulse";
    }

    @Override
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    @Override
    public boolean canAttack() {
        return hasCooledDown();
    }

}
