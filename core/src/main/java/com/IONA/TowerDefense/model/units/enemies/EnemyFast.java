package com.IONA.TowerDefense.model.units.enemies;

import com.badlogic.gdx.math.Rectangle;

public class EnemyFast extends Enemy{
    public EnemyFast(int difficulty){
        super(difficulty);
        hp = 100 + 100*difficulty;
        speed = 300 + 100*difficulty;
        gold = 10;
        setHitBox(0.5f,0.5f);
    }
}
