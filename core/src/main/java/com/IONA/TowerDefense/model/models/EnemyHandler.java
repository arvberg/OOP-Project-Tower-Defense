package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import static com.IONA.TowerDefense.Main.model;

public class EnemyHandler {

    GameModel gamemodel;

    private final List<Enemy> enemies;

    public EnemyHandler(GameModel model) {
        this.gamemodel = model;
        this.enemies = model.getEnemies();
    }

    public void addEnemy(Enemy enemy) {
        Path path = model.getPath();
        Segment first = path.getSegment(0);
        enemy.setToNewSegment(first.getStartPosition(), first.getDirection(), 0);
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) { enemies.remove(enemy); }

    public void removeAllEnemies() {
        enemies.clear();
    }

    public void moveEnemies() {


        List<Enemy> enemies = gamemodel.getEnemies();
        Path path = gamemodel.getPath();

        if (!enemies.isEmpty()) {

            for (Enemy enemy : enemies) {
                int segmentIdx = enemy.getSegmentIndex();
                Segment segment = path.getSegment(segmentIdx);

                Direction enemyDirection = segment.getDirection();
                enemy.move();

                Vector2 segmentEndPoint = segment.getEnd();
                Vector2 enemyCoorPoint = new Vector2(enemy.getPosition().x, enemy.getPosition().y);


                if (enemy.outsideSegment(enemyCoorPoint, segmentEndPoint, enemyDirection)) {
                    int nextIdx = segmentIdx + 1;
                    Segment nextSegment = path.getSegment(nextIdx);

                    enemy.setToNewSegment(nextSegment.getStartPosition(), nextSegment.getDirection(), nextIdx);
                }
            }
        }
    }

    public List<Enemy> removeDeadEnemies() {

        List<Enemy> dead = new ArrayList<>();

        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).getHp() <= 0) {
                dead.add(enemies.get(i));
                enemies.remove(i);
            }
        }

        return dead;
    }

}
