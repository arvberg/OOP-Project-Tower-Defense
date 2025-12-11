package towerTests.towerVariants;

import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.towers.TowerFast;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TowerFactoryTest {

    @Test
    void testCreateTowerBasic() {
        Tower t = TowerFactory.createTower("TowerBasic");
        assertInstanceOf(TowerBasic.class, t);
    }

    @Test
    void testCreateTowerFast() {
        Tower t = TowerFactory.createTower("TowerFast");
        assertInstanceOf(TowerFast.class, t);
    }

    @Test
    void testCreateTowerUnknown() {
        assertThrows(IllegalArgumentException.class,
            () -> TowerFactory.createTower("BadType"));
    }
}
