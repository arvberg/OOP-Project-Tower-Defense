import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.badlogic.gdx.graphics.g3d.Model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.IONA.TowerDefense.*;

public class JUnitTest {

    @Test
    public void enemyTest() {
        GameModel model = new GameModel();
        Enemy enemy = new EnemyBasic(1);
        model.addEnemy(enemy);
        model.removeEnemy(enemy);
        assertEquals(0, model.getEnemies().size());
    }
}
