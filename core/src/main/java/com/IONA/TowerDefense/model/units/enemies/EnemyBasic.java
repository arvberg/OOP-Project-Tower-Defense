package com.IONA.TowerDefense.model.units.enemies;

import com.badlogic.gdx.math.Vector2;

/**
 * EnemyBasic represents a simple, standard enemy type in the game.
 * <p>
 * Extends the abstract Enemy class and sets default stats such as HP, speed,
 * reward money, damage, and size. Also adds visual rotation effects for front and back parts.
 */
public class EnemyBasic extends Enemy {

    private float visualRotationFront = 0;
    private float visualRotationBack = 0;
    public float visualRotationSpeedFront = 100f;
    public float visualRotationSpeedBack = 60f;


    public EnemyBasic(int difficulty) {
        super(difficulty);
        hp = 100 + 25 * difficulty;
        maxHp = 100 + 25 * difficulty;
        speed = 1.3f + 0.1f * difficulty;
        money = 10 - 2 * difficulty;
        damage = 10 + 2 * difficulty;
        width = 0.40f;
        height = 0.40f;
        setHitBox(width, height);

        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }

    @Override
    public void move(float delta) {
        super.move(delta);
        visualRotationBack += visualRotationSpeedBack * delta;
        visualRotationFront += visualRotationSpeedFront * delta;
    }

    public float getVisualRotationFront() {
        return this.visualRotationFront;
    }

    public float getVisualRotationBack() {
        return this.visualRotationBack;
    }


}
