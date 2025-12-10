package enemyTests;

import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyBasicTest {

    @Test
    void testConstructorValues(){
        EnemyBasic e = new EnemyBasic(2);

        assertEquals(400 + 200 * 2, e.getHp());
        assertEquals(400 + 200 * 2, e.getMaxHp());
        assertEquals(2.53f, e.speed);
        assertEquals(25, e.getMoney());
        assertEquals(10, e.getDamageNumber());

        assertNotNull(e.getHitBox());
    }

    @Test
    void testVisualRotation(){
        EnemyBasic e = new EnemyBasic(1);
        float originalFront = e.getVisualRotationFront();
        float originalBack = e.getVisualRotationBack();

        e.move(1f);

        assertTrue(e.getVisualRotationFront() > originalFront);
        assertTrue(e.getVisualRotationBack() > originalBack);
    }

    @Test
    void testTexturesNotNull(){
        EnemyBasic e = new EnemyBasic(1);
        assertNotNull(e.getTexture1());
        assertNotNull(e.getTexture2());
    }
}
