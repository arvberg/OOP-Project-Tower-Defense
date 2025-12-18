package actionTests.actionTypes;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.models.ActionHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.states.StartState;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestartActionTest {
    private GameModel model;
    private ActionHandler handler;

    @BeforeEach
    void setup(){
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;

        model = new GameModel();
        handler = new ActionHandler(model);
    }

    @Test
    void restartAction_resetsToStart(){
        model.setState(model.getGameOverState());

        handler.handleAction(GameAction.RESTART, model.getRestartButton());

        assertInstanceOf(StartState.class, model.getState());
    }
}
