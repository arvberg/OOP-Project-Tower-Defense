package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.model.units.interfaces.EnemyDeathListener;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import static com.IONA.TowerDefense.HeartBeat.delta;
import static com.IONA.TowerDefense.Main.model;

public class EnemyHandler {

    //
    private final List<Enemy> enemies;
    private Path path;

    private final List<EnemyDeathListener> listeners = new ArrayList<>();

    public EnemyHandler(List<Enemy> enemies, Path path) {
        this.enemies = enemies;
        this.path = path;
    }

    public void updateEnemies(float delta) {

        for (Enemy enemy : enemies) {
            if (!enemies.isEmpty()) {
                moveAlongPath(enemy, delta);
            }
        }
        removeDeadEnemies();
    }

    public void moveAlongPath(Enemy enemy, float delta) {
        int segmentIdx = enemy.getSegmentIndex();
        Segment segment = path.getSegment(segmentIdx);

        Direction enemyDirection = segment.getDirection();
        enemy.move(delta);

        Vector2 segmentEndPoint = segment.getEnd();
        Vector2 enemyCoorPoint = new Vector2(enemy.getPosition().x, enemy.getPosition().y);

        if (enemy.outsideSegment(enemyCoorPoint, segmentEndPoint, enemyDirection)) {
            int nextIdx = segmentIdx + 1;
            Segment nextSegment = path.getSegment(nextIdx);

            enemy.setToNewSegment(nextSegment.getStartPosition(), nextSegment.getDirection(), nextIdx);
        }
    }

    public void addEnemy(Enemy enemy) {
        Segment first = path.getSegment(0);
        enemy.setToNewSegment(first.getStartPosition(), first.getDirection(), 0);
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) { enemies.remove(enemy); }

    public void removeAllEnemies() {
        enemies.clear();
    }

    public void removeDeadEnemies() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            if (e.getHp() <= 0) {
                notifyEnemyDeathEvent(e);
                enemies.remove(i);
            }
        }
    }

    public void addAttackListener(EnemyDeathListener l) {
        listeners.add(l);
    }

    public void removeAttackListener(EnemyDeathListener l) {
        listeners.remove(l);
    }

    public void notifyEnemyDeathEvent(Enemy enemy) {
        for (EnemyDeathListener e : listeners) {
            e.onEnemyDeath(enemy);
        }
    }
}
