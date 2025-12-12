package towerTests.towerHandling;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.models.TowerHandler;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class TowerSelectionTest {
    private GameModel model;
    private TowerHandler handler;

    private static class SampleTower extends Tower {
        public SampleTower(float x, float y) {
            this.position = new Vector2(x, y);
            this.dimension = new Vector2(1, 1);
        }
    }

    @BeforeEach
    void setup() {
        model = new GameModel();
        model.getTowers().clear();
        handler = new TowerHandler(model);
    }

    @Test
    void testSelectTower(){
        Tower t = new SampleTower(5, 5);
        model.getTowers().add(t);

        handler.selectTower(new Vector2(5, 5));

        assertEquals(t, model.getSelectedTower());
        assertTrue(model.isTowerSelected());
    }

    @Test
    void testDeselectOnClick(){
        Tower t = new SampleTower(5, 5);
        model.getTowers().add(t);

        handler.selectTower(new Vector2(0, 0));

        assertNull(model.getSelectedTower());
        assertFalse(model.isTowerSelected());
    }

    @Test
    void testSelectNewTower(){
        Tower t1 = new SampleTower(5, 5);
        Tower t2 = new SampleTower(6, 6);
        model.getTowers().add(t1);
        model.getTowers().add(t2);

        handler.selectTower(new Vector2(5, 5));
        assertEquals(t1, model.getSelectedTower());

        handler.selectTower(new Vector2(6, 6));
        assertEquals(t2, model.getSelectedTower());
    }
}
