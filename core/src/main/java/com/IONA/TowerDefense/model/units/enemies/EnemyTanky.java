package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyTanky extends Enemy{
    public EnemyTanky(int difficulty, float width, float height){
        super(difficulty);
        hp = 1000 + 250 * difficulty;
        maxHp = 1000 + 250 * difficulty;

        speed = .1f * difficulty;
        gold = 50;
        damage = 5;
        this.hitBox = new Rectangle();
        hitBox.setPosition(width/2, height/2);

        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }
}
