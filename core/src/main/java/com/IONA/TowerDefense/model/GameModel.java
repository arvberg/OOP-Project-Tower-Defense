package com.IONA.TowerDefense.model;

import com.badlogic.gdx.graphics.Texture;
import sun.tools.jconsole.Plotter;

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
    Texture background;

    private int resources; // Players resources
    private int lives; // Players health
    private int score; // Players current score

    private static final float TOWER_SELECTION_RADIUS = 30f; // Tower selection radius

    private boolean towerSelected = false;
    private Tower pendingTower = null;

    public GameModel (Path path) {
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.path = path;
        this.resources = 100;
        this.score = 0;
        this.background = new Texture("ProtBackground.png");
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

    public void addEnemy(Enemy enemy) { enemies.add(enemy); }

    public void removeEnemy(Enemy enemy) { enemies.remove(enemy); }

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

    public List<Enemy> getEnemies() { return enemies; }

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
        if (pendingTower != null) {

        }
    }

    public void buyTower (Tower tower) {
        pendingTower = tower;
        resources -= tower.getCost();
    }


    // INPUT HANDLING
    // Left-mouse click
    public void onLeftClick(float x, float y) {
        if (towerSelected) {
            // place tower
        }
        else {
            // select tower
        }
    }

    // Right-mouse click
    public void onRightClick(float x, float y) {
        // do something
    }

    // While dragging mouse
    public void onMouseDrag(float x, float y) {
        // do something
    }

}
