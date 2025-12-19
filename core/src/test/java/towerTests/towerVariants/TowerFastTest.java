package towerTests.towerVariants;

import com.IONA.TowerDefense.model.units.towers.TowerFast;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TowerFastTest {

    @Test
    void testInitialStats() {
        TowerFast t = new TowerFast();

        assertEquals(50, t.getDamage());
        assertEquals(8, t.getProjectileSpeed());
        assertEquals(50, t.getCost());
        assertEquals(0.1f, t.getFireRate());
        assertEquals(2f, t.getRange());
        assertEquals(0f, t.getCooldown());
        assertEquals(new Vector2(1f, 1f), t.getDimension());
    }
}

