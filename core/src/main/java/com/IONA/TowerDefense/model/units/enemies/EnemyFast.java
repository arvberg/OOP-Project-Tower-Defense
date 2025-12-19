package com.IONA.TowerDefense.model.units.enemies;

import com.badlogic.gdx.math.Vector2;

/**
 * EnemyFast represents a lightweight, fast-moving enemy.
 * <p>
 * Extends the abstract Enemy class. Compared to EnemyBasic, this enemy has
 * lower HP, higher speed, and slightly lower damage/reward scaling with difficulty.
 * Ideal for testing towers against quick targets.
 */
public class EnemyFast extends Enemy {

    public EnemyFast(int difficulty) {
        super(difficulty);
        hp = 70 + 17 * difficulty;
        maxHp = 70 + 17 * difficulty;
        speed = 2f + 0.2f * difficulty;
        money = 12 - 2 * difficulty;
        damage = 8 + 2 * difficulty;
        width = 0.4f;
        height = 0.4f;
        setHitBox(width, height);

        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }

    @Override
    public void move(float delta) {
        super.move(delta); // behåll all logik från Enemy.update()
    }

}
