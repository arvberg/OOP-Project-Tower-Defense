package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.EnemyDeathListener;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all enemy-related logic, including movement along a path and notifying listeners of enemy deaths.
 */
public class EnemyHandler {

    private final List<Enemy> enemies;
    private final Path path;

    private final List<EnemyDeathListener> listeners = new ArrayList<>();

    public EnemyHandler(List<Enemy> enemies, Path path) {
        this.enemies = enemies;
        this.path = path;
    }

    /**
     * Updates all enemies by moving them along the path and removing dead ones.
     *
     * @param delta the time elapsed since the last update
     */
    public void updateEnemies(float delta) {

        for (Enemy enemy : enemies) {
            if (!enemies.isEmpty()) {
                moveAlongPath(enemy, delta);
            }
        }
        removeDeadEnemies();
    }

    /**
     * Moves a single enemy along its current path segment. If the enemy reaches
     * the end of the segment, it moves to the next segment.
     *
     * @param enemy the enemy to move
     * @param delta the time elapsed since the last update
     */
    public void moveAlongPath(Enemy enemy, float delta) {
        int segmentIdx = enemy.getSegmentIndex();
        Segment segment = path.getSegment(segmentIdx);

        Direction segmentDirection = segment.getDirection();
        enemy.setDirection(segmentDirection);
        enemy.move(delta);

        Vector2 segmentEndPoint = segment.getEnd();

        if (enemy.outsideSegment(segmentEndPoint, segmentDirection)) {
            int nextIdx = segmentIdx + 1;
            Segment nextSegment = path.getSegment(nextIdx);

            enemy.setToNewSegment(nextSegment.getStartPosition(), nextSegment.getDirection(), nextIdx);
        }
    }

    public void addEnemy(Enemy enemy) {
        Segment firstSegment = path.getSegment(0);
        Vector2 firstSegmentStartPosition = firstSegment.getStartPosition();
        Direction firstDirection = firstSegment.getDirection();
        enemy.setToNewSegment(firstSegmentStartPosition, firstDirection, 0);
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void removeAllEnemies() {
        enemies.clear();
    }
    /**
     * Removes enemies that are dead (hp <= 0) and notifies listeners.
     */
    public void removeDeadEnemies() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            if (e.getHp() <= 0) {
                notifyEnemyDeathEvent(e);
                enemies.remove(i);
            }
        }
    }

    public void addEnemyDeathListener(EnemyDeathListener l) {
        listeners.add(l);
    }
    /**
     * Notifies all registered listeners that an enemy has died.
     *
     * @param enemy the enemy that died
     */
    public void notifyEnemyDeathEvent(Enemy enemy) {
        for (EnemyDeathListener e : listeners) {
            e.onEnemyDeath(enemy);
        }
    }
}
