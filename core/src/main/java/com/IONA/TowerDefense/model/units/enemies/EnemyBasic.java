package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.GameModel;
import com.badlogic.gdx.graphics.Texture;

public class EnemyBasic extends Enemy{
    Texture BasicTexture;

    public EnemyBasic(int difficulty, GameModel model){
        super(difficulty, model);
        hp = 400 + 200*difficulty;
        speed = 100 + 50*difficulty;
        gold = 5;

        //img
    }
}
