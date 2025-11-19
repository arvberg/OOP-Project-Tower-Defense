package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.GameModel;
import com.badlogic.gdx.graphics.Texture;

public class EnemyBasic extends Enemy{
    public Texture texture;

    public EnemyBasic(int difficulty, GameModel model){
        super(difficulty, model);
        hp = 400 + 200*difficulty;
        speed = 0.03f;
        gold = 5;
        this.texture = new Texture("ProtTower.png");

        //img
    }
}
