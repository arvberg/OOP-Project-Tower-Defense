package towerTests.towerVariants;

import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TowerBasicTest {

    @Test
    public void testInitialStats(){
        TowerBasic t = new TowerBasic();

        assertEquals(500, t.getDamage());
        assertEquals(8, t.getProjectileSpeed());
        assertEquals(50, t.getCost());
        assertEquals(0.1f, t.getFireRate());
        assertEquals(2f, t.getRange());
        assertEquals(0f, t.getCooldown());
        assertEquals(new Vector2(1f, 1f), t.getDimension());
    }

    @Test
    void testCanShoot(){
        TowerBasic t = new TowerBasic();
        assertTrue(t.canShoot());
    }

    @Test
    void testResetCooldown(){
        TowerBasic t = new TowerBasic();
        t.resetCooldown();
        assertFalse(t.canShoot());
    }
}
