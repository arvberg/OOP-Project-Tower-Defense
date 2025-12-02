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
    private Texture texture2;


    public EnemyBasic(int difficulty){
        super(difficulty);
        texture2 = new Texture("Virus_back.png");
        texture = new Texture("Virus_front.png");
        hp = 400 + 200*difficulty;
        maxHp = 400 + 200*difficulty;
        speed = 2.53f;
        money = 25;
        damage = 1;
        width = 0.5f;
        height = 0.5f;
        setHitBox(width,height);
        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }

    @Override
    public void move() {
        super.move(); // behåll all logik från Enemy.update()
        visualRotationBack  += visualRotationSpeedBack  * delta;
        visualRotationFront += visualRotationSpeedFront * delta;
    }

    public TextureRegion getTexture2(){return new TextureRegion(this.texture2);}
    public TextureRegion getTexture1(){return new TextureRegion(this.texture);}

    public float getVisualRotationFront(){return this.visualRotationFront;}
    public float getVisualRotationBack(){return this.visualRotationBack;}


}
