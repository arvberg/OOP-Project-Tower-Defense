package buttonTests;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.buttonui.PlayButton;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ButtonTest {
    private static class TestButton extends Button {
        boolean clicked = false;


        TestButton(float x, float y, float w, float h) {
            super(x, y, w, h, GameAction.PLAY);
        }
    }

    @Test
    void containsPointInsideBounds() {
        TestButton b = new TestButton(0, 0, 2, 2);

        assertTrue(b.contains(1, 1));
        assertFalse(b.contains(3, 3));
    }

    @Test
    void clickingInsideTriggersOnClick() {
        TestButton b = new TestButton(0, 0, 2, 2);

        b.isClicked(new Vector2(1, 1));

        assertTrue(b.clicked);
    }

    @Test
    void clickingOutsideDoesNothing() {
        TestButton b = new TestButton(0, 0, 2, 2);

        b.isClicked(new Vector2(10, 10));

        assertFalse(b.clicked);
    }

    @Test
    void setButtonPositionUpdatesBounds() {
        TestButton b = new TestButton(0, 0, 2, 2);

        b.setButtonPosition(5, 5);

        assertTrue(b.contains(6, 6));
        assertFalse(b.contains(1, 1));
    }
}
