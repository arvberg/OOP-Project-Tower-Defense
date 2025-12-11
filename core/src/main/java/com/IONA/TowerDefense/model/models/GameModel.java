package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.audio.SoundEvent;
import com.IONA.TowerDefense.model.audio.SoundManager;
import com.IONA.TowerDefense.model.map.Background;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.PathFactory;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.units.decorations.Core;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.ui.buttonui.*;
import com.IONA.TowerDefense.model.ui.playerui.*;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.audio.SoundListener;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


// Main model class to for communication with controller
public class GameModel {
    private GameState gameState = GameState.RUNNING;

    private final List<Tower> towers;
    private final TowerHandler towerHandler;
    private final ResourceHandler resourceHandler;
    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private List<Button> inGameButtons;
    private List<Button> gameOverButtons;
    private List<Menu> menus;

    //private final List<Resource> resources;
    private final List<Resource> resources;
    private final List<Decoration> decorations;
    private Path path;
    private final Background background;
    private final PlayButton playbutton;
    private final ExitButton exitButton;
    private final SpeedUpButton speedUpButton;
    private final PauseButton pauseButton;
    private final RestartButton restartButton;
    private final TowerMenuToggleButton towermenutogglebutton;
    private final UpgradeMenuToggleButton upgrademenutogglebutton;
    private final SideMenuToggleButton sidemenutogglebutton;
    private final AttackHandler attackHandler;
    private final EnemyHandler enemyHandler;
    private final UpgradeHandler upgradeHandler;
    private final SoundManager soundManager;
    private int score; // Players current score
    private final int difficulty;

    private final List<SoundListener> listeners = new ArrayList<>();

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

    private final WaveGenerator generator;

    public GameModel () {

        this.towerMenu = new TowerMenu(13,0,this);
        this.upgradeMenu = new UpgradeMenu(16,0,this);
        this.sideMenu = new SideMenu(13,0);
        this.towers = new ArrayList<>();
        this.towerFactory = new TowerFactory();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.decorations = new ArrayList<>();
        this.generator = new WaveGenerator(0, this);

        //this.money = 100;
        //this.score = 0;
        this.inGameButtons = new ArrayList<>();
        this.background = new Background("Basic");
        this.difficulty = 0;
        this.path = PathFactory.examplePath2();

        this.resourceHandler = new ResourceHandler(this);
        this.resources = resourceHandler.getResources();

        this.attackHandler = new AttackHandler(this);
        this.enemyHandler = new EnemyHandler(this);

        this.towerHandler = new TowerHandler(this);
        this.core = new Core();

        this.upgradeHandler = new UpgradeHandler(this);

        this.soundManager = new SoundManager();
        soundManager.load();

        this.inGameButtons = new ArrayList<>();
        this.gameOverButtons = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.playbutton = new PlayButton(0, 0, this);
        this.exitButton = new ExitButton(500f, 5f);
        this.speedUpButton = new SpeedUpButton(500f, 0);
        this.pauseButton = new PauseButton(10, 0);
        this.restartButton = new RestartButton(5, 5, this);
        this.schanger = new StateChanger();
        this.towermenutogglebutton = new TowerMenuToggleButton(0,8, towerMenu,sideMenu, schanger);
        this.upgrademenutogglebutton = new UpgradeMenuToggleButton(0,3,upgradeMenu,sideMenu, schanger);
        schanger.setButtons(upgrademenutogglebutton,towermenutogglebutton);
        this.sidemenutogglebutton = new SideMenuToggleButton(0, 5,towerMenu,upgradeMenu,sideMenu,schanger);


        inGameButtons.add(towermenutogglebutton);
        inGameButtons.add(upgrademenutogglebutton);
        inGameButtons.add(sidemenutogglebutton);
        inGameButtons.add(playbutton);
        inGameButtons.add(speedUpButton);
        inGameButtons.add(pauseButton);
        gameOverButtons.add(restartButton);
        gameOverButtons.add(exitButton);
        towerMenu.createGridItems(inGameButtons);
        upgradeMenu.createGridItems(inGameButtons);

        // används för att inte kunna placera torn på menues.
        menus.add(towerMenu);
        menus.add(upgradeMenu);
        menus.add(sideMenu);

        placeCore(core);
    }

    public void update(){
        if (gameState == GameState.PAUSED){
            return;
        }
        //System.out.println("updating!");
        updateEnemies(HeartBeat.delta);
        coreDamaged();
        attackHandler.update(HeartBeat.delta);
        towerMenu.update(HeartBeat.delta);
        upgradeMenu.update(HeartBeat.delta);
        sideMenu.update(HeartBeat.delta);
        towermenutogglebutton.updatePosition();
        upgrademenutogglebutton.updatePosition();
        sidemenutogglebutton.updatePosition();

        if (generator.WaveCleared()){
            generator.WaveReward();
            playbutton.toggleButton();
        }
    }

    public Background getBackground(){return this.background;}
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
                int currentLives = resourceHandler.getLives();
                resourceHandler.setLives(currentLives - e.getDamageNumber());
                resourceHandler.updateHpResource();
                removeEnemy(e);
                System.out.println("Health: " + resourceHandler.getLives());
                // Set Game Over state
            }
        }
        if (resourceHandler.getLives() <= 0 && getGameState() != GameState.GAME_OVER) {
            setGameState(GameState.GAME_OVER);
            System.out.println("Game Over!");
            getPlayButton().toggleButton();
            getExitButton().toggleButton();
        }
    }

    public void restartGame() {
        enemyHandler.removeAllEnemies();
        towerHandler.removeAllTowers();
        attackHandler.removeAllProjectiles();
        resourceHandler.resetResources();
        generator.resetWaves();
        setGameState(GameState.START);
        exitButton.toggleButton();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState state) {
        this.gameState = state;
    }

    public void updateEnemies(float delta) {
        enemyHandler.updateEnemies(delta);
    }

    public TowerMenu getTowerMenu(){return this.towerMenu; }

    public Path getPath(){return this.path;}

    public void setPath(Path path){
        this.path = path;
    }
    // Add and remove from list

    public void addTower(Tower tower) {
        towerHandler.addTower(tower);
    }

    public void removeTower(Tower tower) {
        towerHandler.removeTower(tower);
    }

    public void removeEnemy(Enemy enemy) {
        enemyHandler.removeEnemy(enemy);
    }


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
        return resourceHandler.getMoney();
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
        soundManager.playSound("click_tower");
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
            notifySoundEvent(SoundEvent.TOWER_PLACED);
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
        resourceHandler.gainMoney(tower.getCost());
        resourceHandler.updateMoneyResource();
        notifySoundEvent(SoundEvent.TOWER_SOLD);
    }

    public void upgradeTower(Tower tower, TowerUpgrade upgrade) {
        if (resourceHandler.getMoney() >= upgrade.getCost() && selectedTower != null) {
            upgradeHandler.upgrade(tower, upgrade);
            resourceHandler.spendMoney(tower.getCost());
        }
    }

    public void enemyDeath(Enemy enemy) {
        if (getGameState() != GameState.RUNNING) {
            return;
        }
        int moneyGained = enemy.getMoney();
        resourceHandler.gainMoney(moneyGained);
        resourceHandler.updateMoneyResource();
        notifySoundEvent(SoundEvent.ENEMY_BASIC_DEATH);
    }

    public List<Button> getInGameButtons() { return inGameButtons;}

    public List<Button> getGameOverButtons() { return gameOverButtons; }


    public PlayButton getPlayButton(){
        return playbutton;
    }

    public ExitButton getExitButton(){
        return exitButton;
    }

    public SpeedUpButton getSpeedUpButton(){
        return speedUpButton;
    }

    public PauseButton getPauseButton(){return pauseButton;}

    public RestartButton getRestartButton() {
        return restartButton;
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

    public List<Button> getUpgradeMenuItems() {
        return this.upgradeMenu.items;
    }

    public SideMenuToggleButton getSideMenuToggleButton() {
        return this.sidemenutogglebutton;
    }

    public WaveGenerator getGenerator() {
        return generator;
    }

    public SideMenu getSideMenu() {
        return this.sideMenu;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public UpgradeHandler getUpgradeHandler() {
        return this.upgradeHandler;
    }


    public Tower getTowerAt(Vector2 pos) {
        float towerSize = 1.0f;

        for (Tower t : towers) {
            float centerX = t.getPosition().x;
            float centerY = t.getPosition().y;

            // Eftersom positionen nu är tornets mitt
            float halfSize = towerSize / 2f;

            if (pos.x >= centerX - halfSize && pos.x <= centerX + halfSize &&
                pos.y >= centerY - halfSize && pos.y <= centerY + halfSize) {
                return t;
            }
        }
        return null;
    }


    public void addListener(SoundListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SoundListener listener) {
        listeners.remove(listener);
    }

    public void notifySoundEvent(SoundEvent event) {
        for (SoundListener listener : listeners) {
            listener.onSoundEvent(event);
        }
    }

    public SoundManager getSoundManager() {
        return this.soundManager;
    }
}
