package com.IONA.TowerDefense.model.units.enemies;

import com.badlogic.gdx.graphics.Texture;

public class EnemyBasic extends Enemy{
    public Texture texture;

    public EnemyBasic(int difficulty){
        super(difficulty);
        hp = 400 + 200*difficulty;
        speed = 0.1f;
        gold = 5;
        this.texture = new Texture("Enemy_temp_01.png");
        setHitBox(0.5f,0.5f);
    }
}
