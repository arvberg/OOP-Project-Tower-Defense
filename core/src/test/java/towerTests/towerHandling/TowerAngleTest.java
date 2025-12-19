package towerTests.towerHandling;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.models.AttackHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Rotatable;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerAngleTest {

    private static class SampleTower extends Tower implements Rotatable {
        public SampleTower(float x, float y) {
            this.position = new Vector2(x, y);
            this.dimension = new Vector2(1, 1);

            this.range = 999f;

            this.currentDirection = new Vector2(1, 0);
            this.desiredDirection = new Vector2(1, 0);

            this.targetingStrategy = new TargetingStrategy() {
                @Override
                public java.util.List<Enemy> pick(java.util.List<Enemy> enemies, Tower tower) {
                    return enemies;
                }

                @Override
                public String getStrategy() {
                    return "";
                }
            };
        }

        @Override
        public boolean canAttack() {
            return false;
        }

        @Override
        public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
            this.targetingStrategy = targetingStrategy;
        }

        @Override
        public void rotateTower(float delta) {
            // Gör det deterministiskt: "snap" direkt till desired
            this.currentDirection = new Vector2(desiredDirection).nor();
        }

        @Override
        public void setDesiredDirection(Vector2 desiredDirection) {
            this.desiredDirection = desiredDirection;
        }
    }

    private static class SampleEnemy extends Enemy {
        public SampleEnemy(float x, float y) {
            super(1);
            this.position = new Vector2(x, y);
        }

        @Override
        public void move(float dt) { }
    }

    @BeforeEach
    void setup() {
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;
    }

    @Test
    void testAngleUpdates() {
        GameModel model = new GameModel(); // ok att ha kvar, även om den inte används här

        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList.add(new SampleEnemy(1, 0)); // höger

        ArrayList<Tower> towerArrayList = new ArrayList<>();
        SampleTower tower = new SampleTower(0, 0);
        towerArrayList.add(tower);

        ArrayList<Projectile> projectileArrayList = new ArrayList<>();

        AttackHandler handler = new AttackHandler(enemyArrayList, projectileArrayList, towerArrayList);

        handler.updateTowers(1f);

        assertEquals(0f, tower.getAngleDeg(), 0.001f);
    }

    @Test
    void testAngleAimingUpwards() {
        GameModel model = new GameModel();

        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList.add(new SampleEnemy(0, 1)); // uppåt (fix!)

        ArrayList<Tower> towerArrayList = new ArrayList<>();
        SampleTower tower = new SampleTower(0, 0);
        towerArrayList.add(tower);

        ArrayList<Projectile> projectileArrayList = new ArrayList<>();

        AttackHandler handler = new AttackHandler(enemyArrayList, projectileArrayList, towerArrayList);

        handler.updateTowers(1f);

        assertEquals(90f, tower.getAngleDeg(), 0.001f);
    }
}
