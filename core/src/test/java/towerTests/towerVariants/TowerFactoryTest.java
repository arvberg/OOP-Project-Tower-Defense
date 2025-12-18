package towerTests.towerVariants;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.towers.TowerMissile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TowerFactoryTest {

    @Test
    void testCreateTowerBasic() {
        Tower t = TowerFactory.createTower("TowerBasic");
        assertInstanceOf(TowerBasic.class, t);
    }

    @Test
    void testCreateTowerMissile() {
        Tower t = TowerFactory.createTower("TowerFast");
        assertInstanceOf(TowerMissile.class, t);
    }

    @Test
    void testCreateTowerPulse() {
        assertThrows(IllegalArgumentException.class,
            () -> TowerFactory.createTower("BadType"));
    }
}
