package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Main model class to for communication with controller
public class GameModel {

    private List<Tower> towers;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private Path path;


    private int resources; // Players resources
    private int lives; // Players health
    private int score; // Players current score

    private static final float TOWER_SELECTION_RADIUS = 30f; // Tower selection radius

    private boolean towerSelected = false;
    private Tower pendingTower = null;

    public GameModel () {
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.resources = 100;
        this.score = 0;

    }

    // Add and remove from list
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

    // Getters for all lists
    public List<Tower> getTowers() {
        return towers;
    }

    public boolean isTowerSelected() {
        return towerSelected;
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

    // Selecting a tower
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

    public void deselectTower () {
        towerSelected = false;
        pendingTower = null;
    }

    // Placing a tower
    public void placeTower (Point selectedPoint) {
        if (pendingTower != null) {
            pendingTower.setPosition(selectedPoint);
            towers.add(pendingTower);
            pendingTower = null;
        }
    }

    // Buy a tower
    public void buyTower (Tower tower) {
        if (resources >= tower.getCost()) {
            Tower newTower = TowerFactory.createTower(tower.toString(), this);
            pendingTower = newTower;
            resources -= tower.getCost();
        }
    }

}
