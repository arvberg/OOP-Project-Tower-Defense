package com.IONA.TowerDefense.model;

import java.awt.*;

public class EnemyTanky extends Enemy{
    public EnemyTanky(int difficulty, GameModel model){
        super(difficulty, model);
        hp = 1000 + 250 * difficulty;
        speed = 75 + 25 * difficulty;
        gold = 10;
        //img
    }
}
