package towerTests.towerHandling;

import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.models.TowerHandler;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TowerPlacementTest {
    private GameModel model;
    private TowerHandler handler;

    private static class SampleTower extends Tower {
        public SampleTower(Vector2 pos) {
            this.position = pos;
            this.dimension = new Vector2(1, 1);
        }
    }

    private static class SampleDecoration extends Decoration {
        public SampleDecoration(float x, float y, float w, float h) {
            this.position = new Vector2(x, y);
            this.width = w;
            this.height = h;
        }
    }

    @BeforeEach
    void setup(){
        model = new GameModel();
        model.getTowers().clear();
        model.getDecor().clear();

        model.setPath(new Path(new ArrayList<>()));
        handler = new TowerHandler(model);
    }

    @Test
    void testOverlapWithTower(){
        Tower t1 = new SampleTower(new Vector2(5, 5));
        model.getTowers().add(t1);

        Tower t2 = new SampleTower(new Vector2(5, 5)); // same position

        assertTrue(handler.overlaps(t2));
    }

    @Test
    void testOverlapWithDecoration(){
        model.getDecor().add(new SampleDecoration(3, 3, 1, 1));

        Tower t = new SampleTower(new Vector2(3, 3));

        assertTrue(handler.overlaps(t));
    }

    @Test
    void testOverlapsWithPathSegment() {
        List<Segment> segs = new ArrayList<>();
        segs.add(new Segment(new Vector2(0, 0), 2, Direction.EAST));
        model.setPath(new Path(segs));
        handler = new TowerHandler(model);

        Tower t = new SampleTower(new Vector2(1, 0)); // near the segment

        assertTrue(handler.overlaps(t));
    }

    @Test
    void testPlaceTowerSuccess() {
        Tower t = new SampleTower(new Vector2(0, 0));

        model.setPendingTower(t);
        handler.placeTower(new Vector2(2, 2));

        assertEquals(1, model.getTowers().size());
        assertEquals(new Vector2(2, 2), model.getTowers().get(0).getPosition());
        assertNull(model.getPendingTower());
    }

    @Test
    void testTowerCantBePlaced() {
        model.getTowers().add(new SampleTower(new Vector2(4, 4)));

        Tower t = new SampleTower(new Vector2(0, 0));
        model.setPendingTower(t);

        handler.placeTower(new Vector2(4, 4)); // overlap

        assertEquals(1, model.getTowers().size());
        assertNotNull(model.getPendingTower()); // still pending
    }
}
