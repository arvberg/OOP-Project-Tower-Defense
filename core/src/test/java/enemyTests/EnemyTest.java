package enemyTests;

import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new EnemyBasic(1);
        enemy.setToNewSegment(new Vector2(0, 0), Direction.EAST, 0);
    }

    @Test
    void testSetToNewSegment() {
        Vector2 pos = new Vector2(3, 4);
        enemy.setToNewSegment(pos, Direction.NORTH, 2);

        assertEquals(3f, enemy.getPosition().x);
        assertEquals(4f, enemy.getPosition().y);
        assertEquals(2, enemy.getSegmentIndex());
        //TODO: Getter for Cardinal Direction to test that assertEquals(Direction.NORTH, enemy.getDirection());
    }

    @Test
    void testMoveEast(){
        enemy.setToNewSegment(new Vector2(0, 0), Direction.EAST, 0);
        float speed = enemy.speed;

        enemy.move(1f);
        assertEquals(speed, enemy.getPosition().x, 0.001f);
    }

    @Test
    void testMoveNorth() {
        enemy.setToNewSegment(new Vector2(0, 0), Direction.NORTH, 0);
        float speed = enemy.speed;

        enemy.move(1f);
        assertEquals(speed, enemy.getPosition().y, 0.001f);
    }

    @Test
    void testMoveSouth() {
        enemy.setToNewSegment(new Vector2(0, 0), Direction.SOUTH, 0);
        float speed = enemy.speed;

        enemy.move(1f);
        assertEquals(-speed, enemy.getPosition().y, 0.001f);
    }

    @Test
    void testMoveWest() {
        enemy.setToNewSegment(new Vector2(0, 0), Direction.WEST, 0);
        float speed = enemy.speed;

        enemy.move(1f);
        assertEquals(-speed, enemy.getPosition().x, 0.001f);
    }

    @Test
    void testOutsideSegmentNorth() {
        assertTrue(enemy.outsideSegment(new Vector2(5, 10), new Vector2(5, 5), Direction.NORTH));
    }

    @Test
    void testOutsideSegmentSouth() {
        assertTrue(enemy.outsideSegment(new Vector2(5, 1), new Vector2(5, 5), Direction.SOUTH));
    }

    @Test
    void testOutsideSegmentEast() {
        assertTrue(enemy.outsideSegment(new Vector2(10, 5), new Vector2(5, 5), Direction.EAST));
    }

    @Test
    void testOutsideSegmentWest() {
        assertTrue(enemy.outsideSegment(new Vector2(1, 5), new Vector2(5, 5), Direction.WEST));
    }

    @Test
    void testSetHitBox(){
        enemy.setToNewSegment(new Vector2(0, 0), Direction.NORTH, 0);
        enemy.setHitBox(2f, 2f);

        Rectangle box = enemy.getHitBox();

        assertEquals(9f, box.width, 0.001f);
        assertEquals(9f, box.y, 0.001f);
        assertEquals(2f, box.height);
        assertEquals(2f, box.width);

    }

    @Test
    void testTakeDamage(){
        int originalHp = enemy.getHp();

        enemy.takeDamage(50);

        assertEquals(originalHp - 50, enemy.getHp());


    }

}
