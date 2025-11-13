package com.IONA.TowerDefense.model;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Game;

import java.awt.*;

public class EnemyBasic extends Enemy{
    Texture BasicTexture;
    public EnemyBasic(int difficulty){
        super(difficulty);
    public EnemyBasic(int difficulty, GameModel model){
        super(difficulty, model);
        hp = 400 + 200*difficulty;
        speed = 100 + 50*difficulty;
        gold = 5;
        this.BasicTexture = new Texture("ProtTower.png");
        //img
    }
}
