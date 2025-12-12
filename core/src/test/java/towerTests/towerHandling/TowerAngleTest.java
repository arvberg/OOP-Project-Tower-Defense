package towerTests.towerHandling;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.models.AttackHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.models.TowerHandler;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerAngleTest {
    private static class SampleTower extends Tower {
        public SampleTower(float x, float y) {
            this.position = new Vector2(x, y);
            this.dimension = new Vector2(1, 1);
        }
    }

    private static class SampleEnemy extends Enemy{
        public SampleEnemy(float x, float y){
            super(1);
            this.position = new Vector2(x, y);
        }
        @Override public void move(float dt){}
    }

    @BeforeEach
    void setup(){
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;
    }

    @Test
    void testAngleUpdates(){
        GameModel model = new GameModel();

        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        SampleEnemy enemy = new SampleEnemy(1, 0);
        enemyArrayList.add(enemy);

        ArrayList<Tower> towerArrayList = new ArrayList<>();
        SampleTower tower = new SampleTower(0, 0);
        towerArrayList.add(tower);

        ArrayList<Projectile> projectileArrayList = new ArrayList<>();

        AttackHandler handler = new AttackHandler(enemyArrayList, projectileArrayList, towerArrayList);


        tower.setCurrentTarget(enemy);
        handler.updateTowerAngle(tower, enemyArrayList, 1f);

        assertEquals(0f, tower.getAngleDeg(), 0.001f);
    }

    @Test
    void testAngleAimingUpwards(){
        GameModel model = new GameModel();


        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        SampleEnemy enemy = new SampleEnemy(1, 0);
        enemyArrayList.add(enemy);

        ArrayList<Tower> towerArrayList = new ArrayList<>();
        SampleTower tower = new SampleTower(0, 0);
        towerArrayList.add(tower);

        ArrayList<Projectile> projectileArrayList = new ArrayList<>();

        AttackHandler handler = new AttackHandler(enemyArrayList, projectileArrayList, towerArrayList);

        tower.setCurrentTarget(enemy);
        handler.updateTowerAngle(tower, enemyArrayList, 1f);

        assertEquals(90f, tower.getAngleDeg(), 0.001f);
    }
}
