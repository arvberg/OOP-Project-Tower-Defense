package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EnemyBasic extends Enemy{

    public EnemyBasic(int difficulty){
        super(difficulty);
        hp = 400 + 200*difficulty;
        maxHp = 400 + 200*difficulty;
        speed = 2.53f;
        gold = 25;
        damage = 1;
        texture = new Texture("Enemy_temp_03.png");
        width = 0.5f;
        height = 0.5f;
        setHitBox(width,height);

        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }

}
