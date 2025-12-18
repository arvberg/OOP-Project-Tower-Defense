package towerTests.towerHandling;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.models.ResourceHandler;
import com.IONA.TowerDefense.model.models.TowerHandler;
import com.IONA.TowerDefense.model.models.UpgradeHandler;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TowerSelectionTest {
    private GameModel model;
    private TowerHandler handler;
    private Tower tower;

    private static class SampleTower extends Tower {
        public SampleTower(float x, float y) {
            this.position = new Vector2(x, y);
            this.dimension = new Vector2(1, 1);
        }

        @Override
        public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        }
    }

    @BeforeEach
    void setup() {
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;

        model = new GameModel();
        model.getTowers().clear();

        tower = new SampleTower(5, 5);
        model.getTowers().add(tower);

        handler = model.getTowerHandler();
    }

    @Test
    void testSelectTower(){
        handler.selectTower(new Vector2(5, 5));

        assertEquals(tower, model.getSelectedTower());
        assertTrue(model.isTowerSelected());
    }

    @Test
    void testDeselectOnClick(){
        handler.selectTower(new Vector2(0, 0));

        assertNull(model.getSelectedTower());
        assertFalse(model.isTowerSelected());
    }

    @Test
    void testSelectNewTower(){
        Tower t2 = new SampleTower(6, 6);
        model.getTowers().add(t2);

        handler.selectTower(new Vector2(5, 5));
        assertEquals(tower, model.getSelectedTower());

        handler.selectTower(new Vector2(6, 6));
        assertEquals(t2, model.getSelectedTower());
    }
}
