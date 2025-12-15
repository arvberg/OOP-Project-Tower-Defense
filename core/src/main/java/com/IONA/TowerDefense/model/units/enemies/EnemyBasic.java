package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.model.ui.HealthBar;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.HeartBeat.delta;

public class EnemyBasic extends Enemy{

    private float visualRotationFront = 0;
    private float visualRotationBack = 0;
    public float visualRotationSpeedFront = 100f;
    public float visualRotationSpeedBack = 60f;


    public EnemyBasic(int difficulty){
        super(difficulty);
        hp = 100 + 25*difficulty;
        maxHp = 100 + 25*difficulty;
        speed = 1.3f + 0.1f*difficulty;
        money = 10 - 2*difficulty;
        damage = 10 + 2*difficulty;
        width = 0.5f;
        height = 0.5f;
        setHitBox(width,height);

        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }

    @Override
    public void move(float delta) {
        super.move(delta); // behåll all logik från Enemy.update()
        visualRotationBack  += visualRotationSpeedBack  * delta;
        visualRotationFront += visualRotationSpeedFront * delta;
    }

    public float getVisualRotationFront(){return this.visualRotationFront;}
    public float getVisualRotationBack(){return this.visualRotationBack;}


}
