/*
package buttonTests.buttonTypes;

import com.IONA.TowerDefense.Main;
import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.Waves;
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

        model = new GameModel();
        Main.model = model;

        playButton = model.getPlayButton();
    }

    @Test
    void clickingPlaySetsGameRunning() {
        model.setGameState(GameState.START);

        playButton.onClick();

        assertEquals(GameState.RUNNING, model.getGameState());
    }

    @Test
    void clickingPlayAdvancesWave() {
        int initialWave = model.getGenerator().getWaveNr();

        playButton.onClick();

        assertTrue(model.getGenerator().getWaveNr() > initialWave);
    }

    @Test
    void clickingPlayTogglesButtons() {
        float playXBefore = model.getPlayButton().getButtonPosition().x;

        playButton.onClick();

        float playXAfter = model.getPlayButton().getButtonPosition().x;

        assertNotEquals(playXBefore, playXAfter);
    }
}
*/
