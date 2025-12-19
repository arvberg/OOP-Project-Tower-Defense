package enemyTests.enemyVariants;

import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyBasicTest {

    @Test
    void testConstructorValues(){
        EnemyBasic e = new EnemyBasic(2);

        assertEquals(100 + 25 * 2, e.getHp());
        assertEquals(100 + 25 * 2, e.getMaxHp());
        assertEquals(1.5f, e.speed);
        assertEquals(6, e.getMoney());
        assertEquals(104, e.getDamageNumber());

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
}
