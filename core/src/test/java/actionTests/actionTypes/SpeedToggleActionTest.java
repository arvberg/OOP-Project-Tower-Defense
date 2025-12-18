package actionTests.actionTypes;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.models.ActionHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SpeedToggleActionTest {
    private GameModel model;
    private ActionHandler handler;

    @BeforeEach
    void setup() {
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;

        model = new GameModel();
        handler = new ActionHandler(model);
    }

    @Test
    void speedToggleAction_changesGameSpeed() {
        boolean initialSpeed = HeartBeat.getSpeedMultiplier() == 2;

        handler.handleAction(GameAction.SPEED_TOGGLE, model.getSpeedUpButton());

        assertNotEquals(initialSpeed, HeartBeat.getSpeedMultiplier() == 2);
    }

}
