package com.IONA.TowerDefense.model.units.enemies;

import com.badlogic.gdx.math.Rectangle;

public class EnemyTanky extends Enemy{
    public EnemyTanky(int difficulty, float width, float height){
        super(difficulty);
        hp = 1000 + 250 * difficulty;
        speed = 75 + 25 * difficulty;
        gold = 50;
        damage = 5;
        this.hitBox = new Rectangle();
        hitBox.setPosition(width/2, height/2);
    }
}
