package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TowerBasic extends Tower {

    public TowerBasic() {
        dimension = new Vector2(1f, 1f);
        damage = 500;
        projectileSpeed = 8;
        baseFireRate = 0.1f;
        cost = 50;
        fireRate = 0.1f;
        range = 2f;
        baseRange = 2f;
        cooldown = 0f;
        attackStrategy = new ProjectileAttackStrategy();
        //targetingStrategy = new TargetAllStrategy();
        targetingStrategy = new TargetNearestStrategy();
        upgradePath1.add(new FireRateUpgrade(1));
        upgradePath2.add(new RangeUpgrade(1));
    }
}




