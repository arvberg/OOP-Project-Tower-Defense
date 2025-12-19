package com.IONA.TowerDefense.model.units.enemies;

import com.badlogic.gdx.math.Vector2;


/**
 * EnemyTanky represents a slow, high-HP enemy.
 * <p>
 * Extends the abstract Enemy class. Compared to EnemyBasic and EnemyFast,
 * this enemy has significantly higher health, lower speed, and higher damage.
 * Designed to test the player's ability to handle durable, slow-moving targets.
 */
public class EnemyTanky extends Enemy {
    public EnemyTanky(int difficulty) {
        super(difficulty);
        hp = 250 + 50 * difficulty;
        maxHp = 250 + 50 * difficulty;
        speed = 1f + 0.1f * difficulty;
        money = 20 - 2 * difficulty;
        damage = 18 + 6 * difficulty;
        width = 0.6f;
        height = 0.848f;
        setHitBox(width, height);

        this.healthBar = createHealthBar(hp, new Vector2(position), 1f, 0.15f);
    }

    @Override
    public void move(float delta) {
        super.move(delta); // behåll all logik från Enemy.update()
    }

}
