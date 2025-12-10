package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.towers.attackStrategies.LaserAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TowerLaser extends Tower{

    Texture texture2;
    public TowerLaser() {
        dimension = new Vector2(1f, 1f);
        damage = 50;
        baseDamage = 50;
        projectileSpeed = 8;
        cost = 50;
        range = 2f;
        baseRange = 2f;
        cooldown = 0f;
        targetingStrategy = new TargetLeadingStrategy();
        attackStrategy = new LaserAttackStrategy();

        texture = new Texture("Tower_back.png");
        texture2 = new Texture("TowerBasic_01_barrel.png");

        rangeTexture = new TextureRegion(new Texture("Range_01.png"));
    }

    public TextureRegion getTexture2(){
        return new TextureRegion(this.texture2);
    }


}
