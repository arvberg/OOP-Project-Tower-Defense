package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingEnemyStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Vector;

public class TowerBasic extends Tower {

    public TowerBasic() {
        dimension = new Vector2(1f, 1f);
        damage = 50;
        projectileSpeed = 8;
        cost = 50;
        fireRate = 0.1f;
        range = 2;
        cooldown = 0f;
        attackType = "HomingProjectile";
        targetingStrategy = new TargetLeadingEnemyStrategy();

        texture = new Texture("Tower_temp_04.png");
        rangeTexture = new TextureRegion(new Texture("Range_01.png"));
    }

    @Override
    public void attack(Targetable target, long currentTimeMillis) {
    }

    @Override
    public void fire() {
        Vector2 tempPoint = new Vector2(this.getPosition().x, this.getPosition().y);
    }
}
