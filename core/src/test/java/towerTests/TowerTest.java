package towerTests;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    private Tower tower;

    private static class SampleTower extends Tower {
        public SampleTower() {
            dimension = new Vector2(1f, 1f);
            damage = 10;
            projectileSpeed = 5;
            fireRate = 1f;
            cooldown = 0f;
            range = 3f;
            attackStrategy = new ProjectileAttackStrategy();
            targetingStrategy = new TargetLeadingStrategy();
        }

        @Override
        public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
            // VIKTIGT: annars “funkar” setTargetingStrategy aldrig i test-tornet
            this.targetingStrategy = targetingStrategy;
        }

        @Override
        public boolean canAttack() {
            return this.hasCooledDown() && this.isAiming();
        }
    }

    @BeforeEach
    void setup() {
        tower = new SampleTower();
    }

    @Test
    void testCooldownStartsZeroAndCanAttack() {
        assertTrue(tower.hasCooledDown());
    }

    @Test
    void testCooldownResetPreventsShoot() {
        tower.resetCooldown();
        assertFalse(tower.hasCooledDown());
        assertFalse(tower.canAttack());
    }

    @Test
    void testCooldownDecreases() {
        tower.resetCooldown();
        tower.updateCooldown(0.5f);

        assertFalse(tower.hasCooledDown());
        assertFalse(tower.canAttack());

        tower.updateCooldown(0.5f);

        assertTrue(tower.hasCooledDown());
    }

    @Test
    void testSetAndDimensions() {
        Vector2 dim = new Vector2(2f, 3f);
        tower.setDimension(dim);

        assertEquals(2f, tower.getDimension().x, 0.0001);
        assertEquals(3f, tower.getDimension().y, 0.0001);
    }

    @Test
    void testGetTargetReturnsFirstEnemy() {
        List<Enemy> enemies = new ArrayList<>();
        Enemy e1 = new EnemyBasicDud();
        enemies.add(e1);

        List<Enemy> result = tower.getTargets(enemies);

        assertEquals(1, result.size());
        assertSame(e1, result.get(0));
    }

    private static class EnemyBasicDud extends Enemy {
        public EnemyBasicDud() {
            super(1);
        }

        @Override
        public void move(float dt) {}
    }
}
