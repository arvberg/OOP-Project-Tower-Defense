package modelTests;

import com.IONA.TowerDefense.model.models.AttackHandler;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AttackHandlerTest {

    private static class DummyEnemy extends Enemy {
        Vector2 pos;
        Rectangle hitbox;

        DummyEnemy (float x, float y) {
            super(1);
            pos = new Vector2(x, y);
            hitbox = new Rectangle(x, y, 1, 1);
        }

        @Override public Vector2 getPosition() { return pos; }
        @Override public Rectangle getHitBox() { return hitbox; }
        @Override public void move(float dt) {}

        @Override
        public void takeDamage(int damage) {
            this.hp -= damage;
        }

    }

    private static class DummyTower extends Tower {
        Vector2 pos;
        float range;

        DummyTower(float x, float y, float range) {
            pos = new Vector2(x, y);
            this.range = range;
        }

        @Override public Vector2 getPosition() { return pos; }
        @Override public float getRange() { return range; }
        @Override public boolean canAttack() { return false; }
        @Override public void setTargetingStrategy(
            com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy t) {}
    }

    private static class DummyProjectile extends Projectile {
        boolean destroyed = false;

        DummyProjectile() {
            super(1, 1, new Vector2(1,1), new Vector2(1,1));
        }

        @Override public boolean isDestroyed() { return destroyed; }
        @Override public void setDestroyed(boolean v) { destroyed = v; }
        @Override public void move(float dt) {}
    }

    @Test
    void withinRadius_returnWhenClose() {
        Enemy enemy = new DummyEnemy(1,1);
        Tower tower = new DummyTower(0,0,10);

        List<Enemy> enemies = new ArrayList<>();
        List<Tower> towers = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();

        enemies.add(enemy);

        AttackHandler handler = new AttackHandler(enemies, projectiles, towers);
        List<Enemy> result = handler.enemiesInRadius(tower);

        assertSame(result.get(0), enemy);
        assertEquals(1, result.size());
    }
    @Test
    void withinRadius_returnNoEnemiesIfOutOfRange() {
        Enemy enemy = new DummyEnemy(2,1);
        Tower tower = new DummyTower(0,0,1);

        List<Enemy> enemies = new ArrayList<>();
        List<Tower> towers = new ArrayList<>();

        enemies.add(enemy);

        AttackHandler handler = new AttackHandler(enemies, new ArrayList<>(), towers);
        List<Enemy> result = handler.enemiesInRadius(tower);

        assertEquals(0, result.size());
        assertEquals(1, enemies.size());
    }

    @Test
    void removeDeadProjectiles_removesOnlyDestroyedOnes() {
        DummyProjectile alive = new DummyProjectile();
        DummyProjectile dead = new DummyProjectile();
        dead.setDestroyed(true);

        List<Projectile> projectiles = new ArrayList<>();
        projectiles.add(alive);
        projectiles.add(dead);

        AttackHandler handler = new AttackHandler(new ArrayList<>(), projectiles, new ArrayList<>());

        handler.removeDeadProjectiles();

        assertEquals(1, projectiles.size());
        assertSame(alive, projectiles.getFirst());
    }

    @Test
    void isHit_returnsTrueWhenProjectileInsideEnemy() {
        AttackHandler handler = new AttackHandler(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Enemy enemy = new DummyEnemy(0, 0);
        Projectile projectile = new DummyProjectile();
        projectile.setPosition(new Vector2(0,0));

        assertTrue(handler.isHit(projectile, enemy));
    }

    @Test
    void isHit_returnsFalseWhenProjectileInsideEnemy() {
        AttackHandler handler = new AttackHandler(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Enemy enemy = new DummyEnemy(0, 0);
        Projectile projectile = new DummyProjectile();
        projectile.setPosition(new Vector2(5,5));

        assertFalse(handler.isHit(projectile, enemy));
    }
}
