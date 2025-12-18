package towerTests.towerVariants;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TowerBasicTest {

    static class TestableTowerBasic extends TowerBasic {
        private boolean cooledDown;
        private boolean aiming;

        void setCooledDown(boolean value) {
            cooledDown = value;
        }

        void setAiming(boolean value) {
            aiming = value;
        }

        @Override
        public boolean hasCooledDown() {
            return cooledDown;
        }

        @Override
        public boolean isAiming() {
            return aiming;
        }
    }

    @Test
    @DisplayName("Constructor sets default values")
    void constructor_setsDefaults() {
        TowerBasic tower = new TowerBasic();

        assertNotNull(tower.getAttackStrategy());
        assertNotNull(tower.getTargetingStrategy());

        assertEquals(50, tower.getCost());
        assertEquals(5, tower.getDamage());
        assertEquals(2f, tower.getRange(), 0.0001);
        assertEquals(2f, tower.getBaseRange(), 0.0001);

        assertEquals(0f, tower.getCurrentDirection().x, 0.0001);
        assertEquals(0f, tower.getCurrentDirection().y, 0.0001);
    }

    @Test
    @DisplayName("setTargetingStrategy sets given strategy")
    void setTargetingStrategy_setsStrategy() {
        TowerBasic tower = new TowerBasic();
        TargetingStrategy strategy = new TargetNearestStrategy();

        tower.setTargetingStrategy(strategy);

        assertSame(strategy, tower.getTargetingStrategy());
    }

    @Test
    @DisplayName("setDesiredDirection updates desired direction")
    void setDesiredDirection_updatesDesiredDirection() {
        TowerBasic tower = new TowerBasic();
        Vector2 dir = new Vector2(1, 0);

        tower.setDesiredDirection(dir);

        assertEquals(1f, tower.getDesiredDirection().x, 0.0001);
        assertEquals(0f, tower.getDesiredDirection().y, 0.0001);
    }

    @Test
    @DisplayName("rotateTower changes direction when desired is different")
    void rotateTower_changesDirection() {
        TowerBasic tower = new TowerBasic();
        tower.setCurrentDirection(new Vector2(1, 0));
        tower.setDesiredDirection(new Vector2(0, 1));

        tower.rotateTower(0.1f);

        assertNotEquals(1f, tower.getCurrentDirection().x, 0.0001);
    }

    @Test
    @DisplayName("rotateTower does not crash for zero desired direction")
    void rotateTower_zeroDesired_doesNotCrash() {
        TowerBasic tower = new TowerBasic();
        tower.setDesiredDirection(new Vector2(0, 0));

        assertDoesNotThrow(() -> tower.rotateTower(0.1f));
    }

    @Test
    @DisplayName("canAttack only true when cooled down and aiming")
    void canAttack_truthTable() {
        TestableTowerBasic tower = new TestableTowerBasic();

        tower.setCooledDown(false);
        tower.setAiming(false);
        assertFalse(tower.canAttack());

        tower.setCooledDown(false);
        tower.setAiming(true);
        assertFalse(tower.canAttack());

        tower.setCooledDown(true);
        tower.setAiming(false);
        assertFalse(tower.canAttack());

        tower.setCooledDown(true);
        tower.setAiming(true);
        assertTrue(tower.canAttack());
    }
}
