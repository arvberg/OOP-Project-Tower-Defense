package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.*;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.pauseButton;
import com.IONA.TowerDefense.model.ui.playButton;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.MAX_VALUE;

// Main model class to for communication with controller
public class GameModel {
    public boolean paused = false;

    private List<Tower> towers;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private List<Button> buttons;
    private final Path path;
    private PathFactory pathFactory;
    private final Background background;
    public playButton playbutton;
    public pauseButton pausebutton;
    private AttackHandler attackHandler;

    private int resources; // Players resources
    private int lives; // Players health
    private int score; // Players current score
    public static int difficulty;

    private static final float TOWER_SELECTION_RADIUS = 30f; // Tower selection radius

    private TowerFactory towerFactory;
    private boolean towerSelected = false;
    private Tower pendingTower = null;

    public GameModel () {
        this.towers = new ArrayList<>();
        this.towerFactory = new TowerFactory();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.resources = 100;
        this.score = 0;
        this.buttons = new ArrayList<>();
        this.background = new Background();
        this.difficulty = 0;
        this.path = PathFactory.examplePath2();
        AttackHandler attackHandler = new AttackHandler(this);

        this.buttons = new ArrayList<>();
        this.playbutton = new playButton(0, 0, this);
        this.pausebutton = new pauseButton(10, 0);
        buttons.add(playbutton);

        towers.add(new TowerBasic());
    }

    public void moveEnemies() {

        if (!enemies.isEmpty()) {

            for (Enemy enemy : enemies) {
                int segmentIdx = enemy.getSegmentIndex();
                Segment segment = path.getSegment(segmentIdx);

                Direction enemyDirection = segment.getDirection();
                enemy.move();

                Vector2 segmentEndPoint = segment.getEnd();
                Vector2 enemyCoor = enemy.getPosition();
                Vector2 enemyCoorPoint = new Vector2(enemy.getPosition().x, enemy.getPosition().y);


                if (enemy.outsideSegment(enemyCoorPoint, segmentEndPoint, enemyDirection)) {

                    if (enemy.getSegmentIndex() == path.segmentCount() - 1) {
                        loseLife();
                        removeEnemy(enemy);
                        break;
                        //should later also decrement enemies so that the for loop gets shorter when an enemy leaves
                    } else {
                        int nextIdx = segmentIdx + 1;
                        Segment nextSegment = path.getSegment(nextIdx);

                        enemy.setToNewSegment(nextSegment.getStartPosition(), nextSegment.getDirection(), nextIdx);

                    }
                }
            }
        }
    }

    public void updateAttackHandler() {
        attackHandler.update();
    }

    public List<Renderable> getRenderables() {
        List<Renderable> renderables = new ArrayList<>();
        renderables.addAll(towers);
        renderables.addAll(projectiles);
        renderables.addAll(buttons);
        renderables.addAll(enemies);

        return renderables;
    }

    public Path getPath(){return this.path;}
    // Add and remove from list
    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    public void addEnemy(Enemy enemy) {
        Segment first = path.getSegment(0);
        enemy.setToNewSegment(first.getStartPosition(), first.getDirection(), 0);

        enemies.add(enemy);
        }

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

    public void loseLife() {
        lives--;
    }

    public int getScore() {
        return score;
    }

    // Selecting a tower
    public void selectTower(Vector2 selectedPoint) {

        Tower closestTower = null;
        float closestDistance = MAX_VALUE;

        for (Tower tower : towers) {
            Vector2 towerPos = tower.getPosition();
            float distance = selectedPoint.dst(towerPos);

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
    public void placeTower (Vector2 selectedPoint) {
        if (pendingTower != null) {
            pendingTower.setPosition(selectedPoint);
            towers.add(pendingTower);
            pendingTower = null;
        }
    }

    // Buy a tower
    public void buyTower (String tower) {
        Tower newTower = towerFactory.createTower(tower);

        if (resources >= newTower.getCost()) {
            pendingTower = newTower;
            resources -= newTower.getCost();
        }
        else {
            System.out.println("Inte tillräckligt med resurser för att köpa " + tower);
        }
    }

    public Texture getBackground(){
        return background.BackgroundTexture;
    }


    public List<Button> getButtons() { return buttons;}

    public playButton getPlayButton(){
        return playbutton;
    }

    public pauseButton getPauseButton(){
        return pausebutton;
    }


}
