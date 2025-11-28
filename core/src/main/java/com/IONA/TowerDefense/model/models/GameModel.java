package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.*;
import com.IONA.TowerDefense.model.map.Background;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.PathFactory;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.units.decorations.Core;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.ui.buttonui.*;
import com.IONA.TowerDefense.model.ui.playerui.*;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


// Main model class to for communication with controller
public class GameModel {

    public boolean paused = false;

    private final List<Tower> towers;
    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private List<Button> buttons;

    private final List<Resource> resources;
    private final List<Decoration> decorations;
    private final Path path;
    private final Background background;
    private final PlayButton playbutton;
    private final PauseButton pausebutton;
    private final TowerMenuToggleButton towermenutogglebutton;
    private final UpgradeMenuToggleButton upgrademenutogglebutton;
    private final SideMenuToggleButton sidemenutogglebutton;
    private final AttackHandler attackHandler;
    private int gold; // Players gold
    private int lives; // Players health
    private int score; // Players current score
    private final int difficulty;

    private static final float TOWER_SELECTION_RADIUS = 0.65f; // Tower selection radius
    private final TowerFactory towerFactory;
    private boolean towerSelected = false;
    private boolean buyingState = false;

    private Tower pendingTower = null;
    private Tower selectedTower = null;

    private final UpgradeMenu upgradeMenu;
    private final TowerMenu towerMenu;
    private final SideMenu sideMenu;
    private final StateChanger schanger;

    public GameModel () {

        this.towerMenu = new TowerMenu(13,0,this);
        this.upgradeMenu = new UpgradeMenu(16,0,this);
        this.sideMenu = new SideMenu(13,0);
        this.towers = new ArrayList<>();
        this.towerFactory = new TowerFactory();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.decorations = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.lives = 100;
        this.gold = 100;
        this.score = 0;
        this.buttons = new ArrayList<>();
        this.background = new Background();
        this.difficulty = 0;
        this.path = PathFactory.examplePath2();
        this.attackHandler = new AttackHandler(this);


        this.buttons = new ArrayList<>();
        this.playbutton = new PlayButton(0, 0, this);
        this.pausebutton = new PauseButton(10, 0);
        this.schanger = new StateChanger();
        this.towermenutogglebutton = new TowerMenuToggleButton(0,8, towerMenu,sideMenu, schanger);
        this.upgrademenutogglebutton = new UpgradeMenuToggleButton(0,3,upgradeMenu,sideMenu, schanger);
        schanger.setButtons(upgrademenutogglebutton,towermenutogglebutton);
        this.sidemenutogglebutton = new SideMenuToggleButton(0, 5,towerMenu,upgradeMenu,sideMenu,schanger);


        addButtons(towermenutogglebutton);
        addButtons(upgrademenutogglebutton);
        addButtons(sidemenutogglebutton);
        addButtons(playbutton);
        towerMenu.createGridItems(buttons);
        upgradeMenu.createGridItems(buttons);


        resources.add(new ResourceHP(
            lives,
            new Vector2(1.5f, 1.5f),
            3f,
            1f
        ));

        resources.add(new ResourceMoney(
            lives,
            new Vector2(5.5f, 1.5f),
            3f,
            1f));

        placeCore();
    }

    public void placeCore(){
        Decoration core = new Core();
        Segment last = path.getSegment(path.segmentCount()-2);
        Vector2 end = last.getEnd();

        core.setPosition(new Vector2(
            end.x - core.getWidth()/2f,
            end.y - core.getHeight()/2f)
        );

        decorations.add(core);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int hpChange) {
        this.lives = lives + hpChange;
    }

    public void updateHpResource(){
        for (Resource r : resources){
            if (r instanceof ResourceHP){
                r.setCurrentResource(lives);
                r.textBar = String.valueOf(lives);
            }
        }
    }

    public void updateGoldResource(){
        for (Resource r : resources){
            if (r instanceof ResourceMoney){
                r.setCurrentResource(gold);
                r.textBar = String.valueOf(gold);
            }
        }
    }

    public void coreDamaged(){
        if (decorations.isEmpty()) return;
        Rectangle coreHitbox = decorations.get(0).getHitBox();

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);

            if (coreHitbox.overlaps(e.getHitBox())) {
                setLives(e.getDamageNumber());
                updateHpResource();
                removeEnemy(e);

                System.out.println("Health: " + getLives());
            }
        }
    }

    public void moveEnemies() {

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

    public TowerMenu getTowerMenu(){return this.towerMenu; }

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


    // Getters for all lists
    public List<Tower> getTowers() {
        return towers;
    }

    public List<Enemy> getEnemies() { return enemies; }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public List<Decoration> getDecor(){return decorations;}

    public List<Resource> getResources(){return resources;}

    public int getDifficulty() {
        return difficulty;
    }

    // Selecting a tower
// Ny version av selectTower som använder tornets dimensioner
    public void selectTower(Vector2 selectedPoint) {
        Tower clickedTower = null;

        for (Tower tower : towers) {
            // Tornets mittpunkt
            Vector2 center = tower.getPosition(); // Om positionen redan är mittpunkten
            float distance = center.dst(selectedPoint);

            if (distance <= TOWER_SELECTION_RADIUS) {
                clickedTower = tower;
                break; // break om torn hittat
            }
        }
        // Om vi klickar utanför ett torn
        if (clickedTower == null) {
            deselectTower();
            // selecta nytt torn om vi trycker på ett torn
        } else if (selectedTower != clickedTower) {
            selectedTower = clickedTower;
            towerSelected = true;
            System.out.println("Tower selected at: " + selectedTower.getPosition());
        }
    }

    // Deslecting a tower, used in select when outside of radius
    public void deselectTower () {
        selectedTower = null;
        towerSelected = false;
        System.out.println("Tower deselected");
    }

    // Placing a tower
    public void placeTower (Vector2 selectedPoint) {
        if (pendingTower != null) {
            pendingTower.setPosition(selectedPoint);
            gold -= pendingTower.getCost();
            updateGoldResource();
            towers.add(pendingTower);

            selectedTower = pendingTower;
            towerSelected = true;
            System.out.println("Selected tower: " + selectedTower);

            pendingTower = null;
            buyingState = false;
            System.out.println("tower placed");
        }
    }

    // Buy a tower
    public void buyTower (String tower) {
        Tower newTower = towerFactory.createTower(tower);

        if (gold >= newTower.getCost()) {
            buyingState = true;
            pendingTower = newTower;
        }
        else {
            System.out.println("Inte tillräckligt med resurser för att köpa " + tower);
        }
    }

    public void sellTower (Tower tower) {
        if (selectedTower != null) {
            gold += tower.getCost();
            updateGoldResource();
            towers.remove(tower);
        }
    }

    public Texture getBackground(){
        return background.BackgroundTexture;
    }

    public List<Button> getButtons() { return buttons;}

    public void addButtons(Button button) {
        buttons.add(button);
    }

    public void removeButton(Button button) {
        buttons.remove(button);
    }

    public PlayButton getPlayButton(){
        return playbutton;
    }

    public AttackHandler getAttackHandler() {
        return attackHandler;
    }

    public TowerMenuToggleButton getTowerMenuToggleButton() {return towermenutogglebutton;}

    public List<TowerMenuItem> getTowerMenuItems() {
        return towerMenu.items;
    }

    public boolean isBuyingState() {
        return buyingState;
    }

    public Tower getPendingTower() {
        return pendingTower;
    }

    public boolean isTowerSelected() {
        return towerSelected;
    }

    public Tower getSelectedTower() {
        return selectedTower;
    }
    // Make pendingTower follow mouse position after buyTower
    public void updateTowerFollowingMouse(Vector2 mousePos) {
        if (pendingTower != null && buyingState) {
            pendingTower.setPosition(mousePos);
        }
    }

    public UpgradeMenu getUpgradeMenu() {
        return this.upgradeMenu;
    }

    public UpgradeMenuToggleButton getUpgradeMenuToggleButton() {
        return this.upgrademenutogglebutton;
    }

    public List<UpgradeMenuItem> getUpgradeMenuItems() {
        return this.upgradeMenu.items;
    }

    public SideMenuToggleButton getSideMenuToggleButton() {
        return this.sidemenutogglebutton;
    }

    public SideMenu getSideMenu() {
        return this.sideMenu;
    }
}
