package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.GameModel;

public class EnemyFast extends Enemy{
    public EnemyFast(int difficulty, GameModel model){
        super(difficulty, model);
        hp = 100 + 100*difficulty;
        speed = 300 + 100*difficulty;
        gold = 10;
        // img
    }
}
