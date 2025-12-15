package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyFast extends Enemy{

    public EnemyFast(int difficulty){
        super(difficulty);
        hp = 70 + 17*difficulty;
        maxHp = 70 + 17*difficulty;
        speed = 2f + 0.2f*difficulty;
        money = 12 - 2*difficulty;
        damage = 8 + 2*difficulty;
        width = 0.5f;
        height = 0.5f;
        setHitBox(width,height);

        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }

    @Override
    public void move(float delta) {
        super.move(delta); // behåll all logik från Enemy.update()
    }

}
