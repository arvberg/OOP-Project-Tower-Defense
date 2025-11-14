package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.GameModel;

public class EnemyTanky extends Enemy{
    public EnemyTanky(int difficulty){
        super(difficulty);
        hp = 1000 + 250 * difficulty;
        speed = 75 + 25 * difficulty;
        gold = 10;
        //img
    }
}
