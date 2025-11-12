package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Main model class to for communication with controller
public class GameModel {

    private List<Unit> units;
    private List<Tower> towers;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private Path path;
    private Map map;

    private int resources; // Players resources
    private int lives; // Players health
    private int score; // Players current score

    private static final float TOWER_SELECTION_RADIUS = 30f; // Tower selection radius

    private boolean towerSelected = false;
    private Tower pendingTower = null;

    public GameModel (Path path, Map map) {
        this.map = map;
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.path = path;
        this.resources = 100;
        this.score = 0;
    }

    public void update(int delta) {
        moveEnemies(enemies);
        moveProjectiles(projectiles);
        moveTowers(towers);
    }

    public void moveEnemies(List<Enemy> enemies) {

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            int segmentIdx = enemy.getSegmentIndex();
            Segment segment = path.getSegment(segmentIdx);

            Direction enemyDirection = segment.getDirection();
            enemy.move();

            Point segmentEndPoint = segment.getEnd();
            Point enemyCoor = enemy.getCoor();

            if (enemy.outsideSegment(enemyCoor, segmentEndPoint, enemyDirection)) {

                if (enemy.getSegmentIndex() == path.segmentCount()) {
                    loseLife();
                    break;
                } else {
                    Segment nextSegment = path.getSegment(segmentIdx + 1);
                    enemy.setToNewSegment(nextSegment.getStartPoint(), nextSegment.getDirection(), segmentIdx + 1);
                }
            }
        }
    }

    public void moveProjectiles(List<Projectile> projectiles) {

    }

    public void loseLife() {
        lives--;
    }

    // Add and remove towers from list
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void removeProjectiles(Projectile projectile) {
        projectiles.remove(projectile);
    }

    // Getters for lists
    public List<Unit> getUnits() {
        return units;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getResources() {
        return resources;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    // Placing and selecting a tower
    public void selectTower(Point selectedPoint) {
        Tower closestTower = null;
        double closestDistance = Double.MAX_VALUE;

        for (Tower tower : towers) {
            Point towerPos = tower.getPosition();
            double distance = selectedPoint.distance(towerPos);

            if (distance < TOWER_SELECTION_RADIUS && distance < closestDistance) {
                closestDistance = distance;
                closestTower = tower;
            }
        }
        if (closestTower != null) {
            towerSelected = true;
            pendingTower = closestTower;
        }
        else {
            towerSelected = false;
            pendingTower = null;
        }
    }

    public void placeTower (Point selectedPoint) {
    }

    public void buyTower (Tower tower) {
        pendingTower = tower;
        resources -= tower.getCost();
    }
}
