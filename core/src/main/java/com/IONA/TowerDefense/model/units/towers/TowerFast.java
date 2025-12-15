package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TowerFast extends Tower{

    public TowerFast() {
        dimension = new Vector2(1f, 1f);
        damage = 50;
        projectileSpeed = 8;
        cost = 50;
        fireRate = 0.1f;
        baseFireRate = 0.1f;
        range = 2;
        cooldown = 0f;
        targetingStrategy = new TargetLeadingStrategy();
        targetingStrategy = new TargetLeadingStrategy();
    }

    @Override
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {

    }
}
