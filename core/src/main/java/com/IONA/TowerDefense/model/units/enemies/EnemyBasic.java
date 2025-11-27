package com.IONA.TowerDefense.model.units.enemies;

import com.badlogic.gdx.graphics.Texture;

public class EnemyBasic extends Enemy{
    //public Texture texture;

    public EnemyBasic(int difficulty){
        super(difficulty);
        hp = 400 + 200*difficulty;
        speed = 2.53f;
        gold = 25;
        damage = 1;
        this.texture = new Texture("Enemy_temp_03.png");
        this.width = 0.5f;
        this.height = 0.5f;
        setHitBox(width,height);
    }

}
