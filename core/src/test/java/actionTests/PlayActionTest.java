package actionTests;

import com.IONA.TowerDefense.model.GameStateEnum;
import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.models.ActionHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.states.GameState;
import com.IONA.TowerDefense.model.ui.buttonui.PlayButton;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayActionTest {

    private GameModel model;
    private PlayButton playButton;

    @BeforeEach
    void setup() {
        Waves.TEST_MODE = true;
        Decoration.TEST_MODE = true;

        GameModel model = new GameModel();

        playButton = model.getPlayButton();
    }

    @Test
    void playButton_hasAction(){
        PlayButton button = new PlayButton(0, 0);
        assertEquals(GameAction.PLAY, button.getAction());
    }

    @Test
    void playButton_hiddenWhileRunning(){
        model.setGameState(GameStateEnum.RUNNING);
        model.updateButtonLayout();

        assertFalse(model.getPlayButton().isVisible());
        assertTrue(model.getSpeedUpButton().isVisible());
    }

    @Test
    void playAction_startGame(){
        ActionHandler handler = new ActionHandler(model);

        handler.handleAction(GameAction.PLAY, model.getPlayButton());

        assertEquals(GameStateEnum.RUNNING, model.getGameState());
    }
}

