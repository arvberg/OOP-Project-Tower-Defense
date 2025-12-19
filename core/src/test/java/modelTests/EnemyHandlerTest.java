package modelTests;

import com.IONA.TowerDefense.model.map.PathFactory;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.IONA.TowerDefense.model.map.Path;

public class EnemyHandlerTest {

    private static class DummyEnemy extends Enemy {
        Vector2 pos;
        Rectangle hitbox;

        DummyEnemy(float x, float y) {
            super(1);
            pos = new Vector2(x, y);
            hitbox = new Rectangle(x, y, 1, 1);
            this.maxHp = 10;
            this.hp = 10;
        }

        @Override public Vector2 getPosition() { return pos; }
        @Override public Rectangle getHitBox() { return hitbox; }

        @Override
        public void move(float dt) {
            hitbox.setPosition(pos.x, pos.y);
        }

        void setPos(float x, float y) {
            pos.set(x, y);
            hitbox.setPosition(x, y);
        }

        @Override
        public void takeDamage(int damage) {
            hp = Math.max(0, hp - damage);
        }

    }

    private static class DummyPath extends Path {
        public DummyPath() {
            super(PathFactory.examplePath1().getSegments());
        }
    }
}
