package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TowerBasic extends Tower {

    Texture texture2;
    public TowerBasic() {
        dimension = new Vector2(1f, 1f);
        damage = 50;
        projectileSpeed = 8;
        baseFireRate = 0.1f;
        cost = 50;
        fireRate = 0.1f;
        range = 2f;
        baseRange = 2f;
        cooldown = 0f;
        attackStrategy = new AreaAttackStrategy();
        targetingStrategy = new TargetAllStrategy();

        texture = new Texture("Tower_back.png");
        texture2 = new Texture("TowerBasic_01_barrel.png");

        rangeTexture = new TextureRegion(new Texture("Range_01.png"));
    }

    public TextureRegion getTexture2(){
        return new TextureRegion(this.texture2);
    }

}
