package com.IONA.TowerDefense.model;

import java.awt.*;

public class EnemyFast extends Enemy{
    public EnemyFast(int difficulty){
        super(difficulty);
        hp = 100 + 100*difficulty;
        speed = 300 + 100*difficulty;
        gold = 10;
        // img
    }
}
