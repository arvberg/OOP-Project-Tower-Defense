package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyFast extends Enemy{
    public EnemyFast(int difficulty){
        super(difficulty);
        hp = 100 + 100*difficulty;
        maxHp = 100 + 100*difficulty;

        speed = 3f * difficulty;
        gold = 10;
        damage = 2;
        setHitBox(0.5f,0.5f);

        this.healthBar = new HealthBar(hp, new Vector2(position), 1f, 0.15f);
    }
}
