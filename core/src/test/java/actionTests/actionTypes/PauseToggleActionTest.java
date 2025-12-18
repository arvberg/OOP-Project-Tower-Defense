package actionTests.actionTypes;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.models.ActionHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.states.PauseState;
import com.IONA.TowerDefense.model.states.RunningState;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PauseToggleActionTest {
    private GameModel model;
    private ActionHandler handler;

    @BeforeEach
    void setup() {
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;

        model = new GameModel();
        handler = new ActionHandler(model);

        // must be running to pause
        model.setState(model.getRunningState());
    }

    @Test
    void pauseToggle_pauses(){
        handler.handleAction(GameAction.PAUSE_TOGGLE, model.getPauseButton());
        assertInstanceOf(PauseState.class, model.getState());
    }

    @Test
    void pauseToggle_resumes(){
        handler.handleAction(GameAction.PAUSE_TOGGLE, model.getPauseButton());
        assertInstanceOf(PauseState.class, model.getState());

        handler.handleAction(GameAction.PAUSE_TOGGLE, model.getPauseButton());
        assertInstanceOf(RunningState.class, model.getState());
    }
}
