package com.IONA.TowerDefense.model;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class EnemyBasic extends Enemy{
    Texture BasicTexture;
    public EnemyBasic(int difficulty){
        super(difficulty);
        hp = 400 + 200*difficulty;
        speed = 100 + 50*difficulty;
        gold = 5;
        this.BasicTexture = new Texture("ProtTower.png");
        //img
    }
}
