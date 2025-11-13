package com.IONA.TowerDefense.model.units.enemies;

public class EnemyBasic extends Enemy {
    public EnemyBasic(int difficulty){
        super(difficulty);
        hp = 400 + 200*difficulty;
        speed = 100 + 50*difficulty;
        gold = 5;
        //img
    }
}
