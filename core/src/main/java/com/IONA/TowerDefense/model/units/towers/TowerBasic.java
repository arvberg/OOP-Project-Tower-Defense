package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.w3c.dom.ranges.Range;

public class TowerBasic extends Tower {

    public TowerBasic() {
        dimension = new Vector2(0.8f, 0.8f);
        damage = 5;
        projectileSpeed = 8;
        baseFireRate = 0.01f;
        cost = 50;
        fireRate = 0.55f;
        range = 2f;
        baseRange = 2f;
        cooldown = 0f;
        rotationSpeed = 20f;
        aimingMargin = 1f;
        attackStrategy = new ProjectileAttackStrategy();
        targetingStrategies.add(new TargetLeadingStrategy()); // The order that you add strategies is very important.
        targetingStrategies.add(new TargetNearestStrategy());
        targetingStrategy = targetingStrategies.getFirst();
        upgradePath1.add(new RangeUpgrade(40));
        upgradePath2.add(new RangeUpgrade(40));
        upgradePath3.add(new RangeUpgrade(40));
        towerType = "TowerBasic";
    }


}




