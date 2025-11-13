package com.IONA.TowerDefense.model;

import com.badlogic.gdx.Game;

import java.awt.*;

public class EnemyBasic extends Enemy{
    public EnemyBasic(int difficulty, GameModel model){
        super(difficulty, model);
        hp = 400 + 200*difficulty;
        speed = 100 + 50*difficulty;
        gold = 5;
    }
}
