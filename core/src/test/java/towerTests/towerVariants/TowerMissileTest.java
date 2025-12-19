package towerTests.towerVariants;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.TowerMissile;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TowerMissileTest {
    static class TestableTowerMissile extends TowerMissile {
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
    @DisplayName("setTargetingStrategy sets given strategy")
    void setTargetingStrategy_setsStrategy() {
        TowerMissile tower = new TowerMissile();
        TargetingStrategy strategy = new TargetNearestStrategy();

        tower.setTargetingStrategy(strategy);

        assertSame(strategy, tower.getTargetingStrategy());
    }

    @Test
    @DisplayName("setDesiredDirection updates desired direction")
    void setDesiredDirection_updatesDesiredDirection() {
        TowerMissile tower = new TowerMissile();
        Vector2 dir = new Vector2(1, 0);

        tower.setDesiredDirection(dir);

        assertEquals(1f, tower.getDesiredDirection().x, 0.0001);
        assertEquals(0f, tower.getDesiredDirection().y, 0.0001);
    }

    @Test
    @DisplayName("rotateTower changes direction when desired is different")
    void rotateTower_changesDirection() {
        TowerMissile tower = new TowerMissile();
        tower.setCurrentDirection(new Vector2(1, 0));
        tower.setDesiredDirection(new Vector2(0, 1));

        tower.rotateTower(0.1f);

        assertNotEquals(1f, tower.getCurrentDirection().x, 0.0001);
    }

    @Test
    @DisplayName("rotateTower does not crash for zero desired direction")
    void rotateTower_zeroDesired_doesNotCrash() {
        TowerMissile tower = new TowerMissile();
        tower.setDesiredDirection(new Vector2(0, 0));

        assertDoesNotThrow(() -> tower.rotateTower(0.1f));
    }

    @Test
    @DisplayName("canAttack only true when cooled down and aiming")
    void canAttack_truthTable() {
        TestableTowerMissile tower = new TestableTowerMissile();

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
