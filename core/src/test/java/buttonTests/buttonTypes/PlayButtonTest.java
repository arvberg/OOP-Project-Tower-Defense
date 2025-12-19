package buttonTests.buttonTypes;

import com.IONA.TowerDefense.Main;
import com.IONA.TowerDefense.controller.InputHandler;
import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.models.ActionHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.PlayButton;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayButtonTest {

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
        model.setGameState(GameState.RUNNING);
        model.updateButtonLayout();

        assertFalse(model.getPlayButton().isVisible());
        assertTrue(model.getSpeedUpButton().isVisible());
    }

    @Test
    void clickingPlayButton_dispatchesPlayAction() {
        InputHandler input = new InputHandler(model);

        PlayButton play = model.getPlayButton();
        play.setVisible(true);

        input.checkInput(play.getButtonPosition());

        assertEquals(GameState.RUNNING, model.getGameState());
    }

    @Test
    void playAction_startGame(){
        ActionHandler handler = new ActionHandler(model);

        handler.handleAction(GameAction.PLAY, model.getPlayButton());

        assertEquals(GameState.RUNNING, model.getGameState());
    }
}

