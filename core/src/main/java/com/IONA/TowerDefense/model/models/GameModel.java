package com.IONA.TowerDefense.model.models;

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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


// Main model class to for communication with controller
public class GameModel {

    public boolean paused = false;

    private final List<Tower> towers;
    private final TowerHandler towerHandler;
    private final ResourceHandler resourceHandler;
    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private List<Button> buttons;

    //private final List<Resource> resources;
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
    private final EnemyHandler enemyHandler;
    private int lives; // Players health
    private int score; // Players current score
    private int money;
    private final int difficulty;

    private final TowerFactory towerFactory;
    private boolean towerSelected = false;
    private boolean buyingState = false;

    private Tower pendingTower = null;
    private Tower selectedTower = null;

    private final UpgradeMenu upgradeMenu;
    private final TowerMenu towerMenu;
    private final SideMenu sideMenu;
    private final StateChanger schanger;

    private final Decoration core;

    public GameModel () {

        this.towerMenu = new TowerMenu(13,0,this);
        this.upgradeMenu = new UpgradeMenu(16,0,this);
        this.sideMenu = new SideMenu(13,0);
        this.towers = new ArrayList<>();
        this.towerFactory = new TowerFactory();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.decorations = new ArrayList<>();

        //this.money = 100;
        //this.score = 0;
        this.buttons = new ArrayList<>();
        this.background = new Background();
        this.difficulty = 0;
        this.path = PathFactory.examplePath2();
        this.attackHandler = new AttackHandler(this);
        this.enemyHandler = new EnemyHandler(this);

        this.resourceHandler = new ResourceHandler(this);
        this.resources = resourceHandler.getResources();
        this.money = resourceHandler.getMoney();
        this.lives = resourceHandler.getLives();

        this.towerHandler = new TowerHandler(this);
        this.core = new Core();


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

        placeCore(core);
    }

    public void placeCore(Decoration core){
        Segment last = path.getSegment(path.segmentCount()-2);
        Vector2 end = last.getEnd();

        core.setPosition(new Vector2(
            end.x+0.1f,
            end.y)
        );

        decorations.add(core);
    }

    public void coreDamaged(){
        if (decorations.isEmpty()) return;
        Rectangle coreHitbox = decorations.get(0).getHitBox();

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);

            if (coreHitbox.overlaps(e.getHitBox())) {
                resourceHandler.setLives(e.getDamageNumber());
                resourceHandler.updateHpResource();
                removeEnemy(e);

                System.out.println("Health: " + resourceHandler.getLives());
            }
        }
    }

    public void updateEnemies() {
        enemyHandler.moveEnemies();
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ResourceHandler getResourceHandler(){
        return resourceHandler;
    }

    public int getDifficulty() {
        return difficulty;
    }

    // Selecting a tower
    public void selectTower(Vector2 selectedPoint) {
        towerHandler.selectTower(selectedPoint);
    }

    // Deslecting a tower, used in select when outside of radius
    public void deselectTower () {
        towerHandler.deselectTower();
    }

    // Placing a tower
    public void placeTower (Vector2 selectedPoint) {
        // placera genom towerHandler
        towerHandler.placeTower(selectedPoint);
        Tower tower = getSelectedTower();
        // Minska pengar genom resourceHandler
        if (tower != null) {
            resourceHandler.spendMoney(tower.getCost());
            resourceHandler.updateMoneyResource();
        }
    }

    public boolean overlaps(Tower tower) {
        return towerHandler.overlaps(tower);
    }

    // Buy a tower
    public void buyTower(String tower) {
        towerHandler.buyTower(tower);
    }

    public void sellTower(Tower tower) {
        towerHandler.sellTower(tower);
        money += (int) (pendingTower.getCost() * 0.8);
        resourceHandler.updateMoneyResource();
    }

    // Tar bort fiender genom enemyHandler och ger pengar genom resourceHandler
    public void removeDeadEnemies() {

        List<Enemy> deadEnemies = enemyHandler.removeDeadEnemies();

        for (Enemy enemy : deadEnemies) {
            int moneyGained = enemy.getMoney();
            resourceHandler.gainMoney(moneyGained);
        }

        resourceHandler.updateMoneyResource();
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

    public TowerFactory getTowerFactory() {
        return towerFactory;
        }

    public TowerMenuToggleButton getTowerMenuToggleButton() {return towermenutogglebutton;}

    public List<TowerMenuItem> getTowerMenuItems() {
        return towerMenu.items;
    }

    public boolean isBuyingState() {
        return buyingState;
    }

    public void setBuyingState(Boolean bool) {
        this.buyingState = bool;
    }

    public Tower getPendingTower() {
        return pendingTower;
    }

    public void setPendingTower(Tower tower) {
        this.pendingTower = tower;
    }

    public Tower getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(Tower tower) {
        this.selectedTower = tower;
    }

    public boolean isTowerSelected() {
        return towerSelected;
    }

    public void setTowerSelected(Boolean bool) {
        this.towerSelected = bool;
    }

    public void updateTowerAngle(Tower tower){towerHandler.updateTowerAngle(tower);}

    // Make pendingTower follow mouse position after buyTower
    public void updateTowerFollowingMouse(Vector2 mousePos) {
        if (pendingTower != null && buyingState) {
            pendingTower.setPosition(mousePos);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemyHandler.addEnemy(enemy);
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
