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

    private static class SampleTower extends Tower{
        public SampleTower(){
            dimension = new Vector2(1f, 1f);
            damage = 10;
            projectileSpeed = 5;
            fireRate = 1f;
            cooldown = 0;
            range = 3f;
            attackStrategy = new ProjectileAttackStrategy();
            targetingStrategy = new TargetLeadingStrategy();
        }

        @Override
        public void setTargetingStrategy(TargetingStrategy targetingStrategy) {

        }
    }

    @BeforeEach
    void setup(){
        tower = new SampleTower();
    }

    @Test
    void testCooldownStartsZeroAndCanShoot(){
        assertTrue(tower.canShoot());
    }

    @Test
    void testCooldownResetPreventsShoot(){
        tower.resetCooldown();
        assertFalse(tower.canShoot());
    }

    @Test
    void testCooldownDecreases(){
        tower.resetCooldown();
        tower.updateCooldown(0.5f);
        assertFalse(tower.canShoot());

        tower.updateCooldown(0.5f);
        assertTrue(tower.canShoot());
    }

    @Test
    void testSetAndDimensions(){
        Vector2 dim = new Vector2(2f, 3f);
        tower.setDimension(dim);
        assertEquals(dim, tower.getDimension());
    }

    @Test
    void testGetTargetReturnsFirstEnemy(){
        List<Enemy> enemies = new ArrayList<>();

        Enemy e1 = new EnemyBasicDud();
        Enemy e2 = new EnemyBasicDud();

        enemies.add(e1);
        enemies.add(e2);

        List<Enemy> result = tower.getTargets(enemies);
        assertEquals(1, result.size());
        assertEquals(e1, result.getFirst());
    }

    private static class EnemyBasicDud extends Enemy{
        public EnemyBasicDud(){
            super(1);
        }

        @Override
        public void move(float dt){}
    }

}
