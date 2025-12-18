/*
package enemyTests.enemyHandling;

import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.models.EnemyHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyModelTest {


    private EnemyHandler handler;
    private GameModel model;

    @BeforeEach
    void setup(){
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;

        model = new GameModel();
        handler = new EnemyHandler( model.getEnemies(), model.getPath());
    }

    @Test
    void testAddEnemy(){
        Enemy e = new EnemyBasic(1);
        handler.addEnemy(e);

        assertEquals(1, model.getEnemies().size());
        assertTrue(model.getEnemies().contains(e));


    }

    @Test
    void testRemoveEnemy() {
        Enemy e = new EnemyBasic(1);
        handler.addEnemy(e);
        handler.removeEnemy(e);

        assertEquals(0, model.getEnemies().size());
    }

    @Test
    void testUpdateRemovesDeadEnemies() {
        Enemy e = new EnemyBasic(1);
        handler.addEnemy(e);

        e.takeDamage(e.getHp()); // kill enemy

        handler.removeDeadEnemies();

        assertEquals(0, model.getEnemies().size());
    }

    @Test
    void testUpdateMovesEnemies() {
        Enemy e = new EnemyBasic(1);
        handler.addEnemy(e);

        e.setToNewSegment(new Vector2(0,0), Direction.EAST, 0);
        float before = e.getPosition().x;

        handler.moveAlongPath(e, 1f);

        assertTrue(e.getPosition().x > before, "Enemy should have moved east");
    }

    @Test
    void testClear() {
        handler.addEnemy(new EnemyBasic(1));
        handler.addEnemy(new EnemyBasic(1));

        handler.removeAllEnemies();

        assertEquals(0, model.getEnemies().size());
    }

    @Test
    void testGetAliveCount() {
        Enemy a = new EnemyBasic(1);
        Enemy b = new EnemyBasic(1);

        handler.addEnemy(a);
        handler.addEnemy(b);

        b.takeDamage(b.getHp()); // kill one

        assertEquals(1, model.getEnemies().size());
    }
}
*/
