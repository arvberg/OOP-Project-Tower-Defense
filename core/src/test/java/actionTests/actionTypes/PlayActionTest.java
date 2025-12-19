package actionTests.actionTypes;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.models.ActionHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.states.RunningState;
import com.IONA.TowerDefense.model.states.StartState;
import com.IONA.TowerDefense.controller.buttonui.PlayButton;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayActionTest {

    private GameModel model;

    @BeforeEach
    void setup() {
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;

        model = new GameModel();
    }

    @Test
    void playButton_hasAction(){
        PlayButton button = new PlayButton(0, 0);
        assertEquals(GameAction.PLAY, button.getAction());
    }

    @Test
    void playButton_hiddenWhileRunning(){
        model.setState(model.getRunningState());
        model.updateButtonLayout();

        assertFalse(model.getPlayButton().isVisible());
        assertTrue(model.getSpeedUpButton().isVisible());
    }

    @Test
    void playAction_startGame(){
        ActionHandler handler = new ActionHandler(model);

        assertInstanceOf(StartState.class, model.getState());

        handler.handleAction(GameAction.PLAY, model.getPlayButton());

        assertInstanceOf(RunningState.class, model.getState());
    }
}

