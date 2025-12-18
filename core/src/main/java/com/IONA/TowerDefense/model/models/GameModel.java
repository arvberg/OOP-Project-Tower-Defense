package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.input.GameAction;
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
import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.model.units.interfaces.EnemyDeathListener;
import com.IONA.TowerDefense.model.units.interfaces.TowerListener;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


// Main model class to for communication with controller
public class GameModel implements EnemyDeathListener, AttackListener, TowerListener {
    private GameState gameState = GameState.START;

    private final List<Tower> towers;
    private final TowerHandler towerHandler;
    private final UpgradeHandler upgradeHandler;
    private final ResourceHandler resourceHandler;
    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private List<Button> inGameButtons;
    private List<Button> gameOverButtons;
    private List<Button> towerItemButtons;
    private List<Menu> menus;

    //private final List<Resource> resources;
    private final List<Resource> resources;
    private final List<Decoration> decorations;
    private Path path;
    private final ActionHandler actionHandler;
    private final PlayButton playButton;
    private final ExitButton exitButton;
    private final SpeedUpButton speedUpButton;
    private final PauseButton pauseButton;
    private final RestartButton restartButton;
    private final TargetingStrategyToggleButton targetingStrategyToggleButton;
    private final AttackHandler attackHandler;
    private final EnemyHandler enemyHandler;
    private final int difficulty;

    private final TowerFactory towerFactory;

    private final TowerMenu towerMenu;
    private final InfoMenu infoMenu;
    private final UpgradeMenu upgradeMenu;

    private final Decoration core;

    private final WaveGenerator generator;

    private String background;

    public GameModel () {

        this.towerMenu = new TowerMenu(12,7.5f,this);
        this.infoMenu = new InfoMenu(16, 9, this);
        this.upgradeMenu = new UpgradeMenu(16, 9, this);
        this.towers = new ArrayList<>();
        this.towerFactory = new TowerFactory();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.decorations = new ArrayList<>();
        this.generator = new WaveGenerator(0, this);
        this.core = new Core();

        this.inGameButtons = new ArrayList<>();
        this.towerItemButtons = new ArrayList<>();
        this.background = "Starter map";
        this.difficulty = 0;
        this.path = PathFactory.examplePath2();

        this.resourceHandler = new ResourceHandler();
        this.resources = resourceHandler.getResources();

        this.attackHandler = new AttackHandler(enemies, projectiles, towers);
        attackHandler.addAttackListener(this);
        this.enemyHandler = new EnemyHandler(enemies, path);

        this.towerHandler = new TowerHandler(towers, towerFactory, path, decorations, resourceHandler, upgradeMenu);
        this.upgradeHandler = new UpgradeHandler();

        this.actionHandler = new ActionHandler(this);

        this.inGameButtons = new ArrayList<>();
        this.gameOverButtons = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.playButton = new PlayButton(14.8f, 0.2f);
        this.exitButton = new ExitButton(10f, 3f);
        this.speedUpButton = new SpeedUpButton(0, 0);
        this.pauseButton = new PauseButton(10, 0);
        this.restartButton = new RestartButton(5f, 3f);
        this.targetingStrategyToggleButton = new TargetingStrategyToggleButton(5, 5);


        inGameButtons.add(playButton);
        inGameButtons.add(speedUpButton);
        inGameButtons.add(pauseButton);
        gameOverButtons.add(restartButton);
        gameOverButtons.add(exitButton);
        towerMenu.createGridItems(towerItemButtons);


        // används för att inte kunna placera torn på menues.
        menus.add(towerMenu);
        menus.add(infoMenu);
        //menus.add(upgradeMenu);

        placeCore(core);
        updateButtonLayout();
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
        infoMenu.update(HeartBeat.delta);

        if (generator.WaveCleared()){
            generator.WaveReward();
            playButton.setVisible(true);
            setGameState(GameState.START);
            updateButtonLayout();
        }
    }

    public String getBackground(){return this.background;}

    public void setBackground(String background){
        this.background = background;
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
                int currentLives = resourceHandler.getLives();
                resourceHandler.setLives(currentLives - e.getDamageNumber());
                resourceHandler.updateHpResource();
                enemyHandler.removeEnemy(e);
                System.out.println("Health: " + resourceHandler.getLives());
                // Set Game Over state
            }
        }
        if (resourceHandler.getLives() <= 0 && getGameState() != GameState.GAME_OVER) {
            setGameState(GameState.GAME_OVER);
            System.out.println("Game Over!");
            updateButtonLayout();
            restartButton.setVisible(true);
            exitButton.setVisible(true);
        }
    }

    public void restartGame() {
        enemyHandler.removeAllEnemies();
        towerHandler.removeAllTowers();
        attackHandler.removeAllProjectiles();
        resourceHandler.resetResources();
        generator.resetWaves();

        setGameState(GameState.START);
        //exitButton.setVisible(true);
        updateButtonLayout();
    }

    public void handleAction(GameAction action, Button sourceButton) { actionHandler.handleAction(action, sourceButton); }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState state) {
        this.gameState = state;
    }

    public void updateEnemies(float delta) {
        enemyHandler.updateEnemies(delta);
    }

    void updateButtonLayout(){
        boolean running = gameState == GameState.RUNNING;

        playButton.setVisible(!running);
        speedUpButton.setVisible(running);
    }

    public TowerMenu getTowerMenu(){return this.towerMenu; }

    public Path getPath(){return this.path;}

    public void setPath(Path path){
        this.path = path;
    }
    // Add and remove from list

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
    }

    // Deslecting a tower, used in select when outside of radius
    public void deselectTower () {
        towerHandler.deselectTower();
    }

    // Placing a tower
    public void placeTower (Vector2 selectedPoint) {
        // placera genom towerHandler
        towerHandler.placeTower(selectedPoint);

        Tower tower = towerHandler.getSelectedTower();
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
        resourceHandler.gainMoney(tower.getCost());
        resourceHandler.updateMoneyResource();
    }

    public void cancelTowerBuy() {
        towerHandler.cancelBuy();
    }

    public void upgradeTower(Tower tower, TowerUpgrade upgrade) {
        if (resourceHandler.getMoney() >= upgrade.getCost() && towerHandler.getSelectedTower() != null) {
            upgradeHandler.upgrade(tower, upgrade);
            resourceHandler.spendMoney(upgrade.getCost());
            resourceHandler.updateMoneyResource();

        }
    }

    public Tower getSelectedTower() {
        return towerHandler.getSelectedTower();
    }

    public void setSelectedTower(Tower tower) {
        towerHandler.setSelectedTower(tower);
    }

    public boolean isTowerSelected() {
        return towerHandler.isTowerSelected();
    }

    public void setTowerSelected(Boolean bool) {
        towerHandler.setTowerSelected(bool);
    }

    public boolean isBuyingState() {
        return towerHandler.isBuyingState();
    }

    public void setBuyingState(Boolean bool) {
        towerHandler.setBuyingState(bool);
    }

    public Tower getPendingTower() {
        return towerHandler.getPendingTower();
    }

    public void setPendingTower(Tower tower) {
        towerHandler.setPendingTower(tower);
    }

    public void enemyDeath(Enemy enemy) {
        if (getGameState() != GameState.RUNNING) {
            return;
        }
        int moneyGained = enemy.getMoney();
        resourceHandler.gainMoney(moneyGained);
        resourceHandler.updateMoneyResource();
    }

    public List<Button> getInGameButtons() { return inGameButtons;}

    public List<Button> getTowerItemButtons() { return towerItemButtons;}

    public List<Button> getGameOverButtons() { return gameOverButtons; }

    public PlayButton getPlayButton(){
        return playButton;
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

    public List<TowerMenuItem> getTowerMenuItems() {
        return towerMenu.items;
    }

    public List<Button> getUpgradeMenuItems(){
        return upgradeMenu.items;
    }


    // Make pendingTower follow mouse position after buyTower
    public void updateTowerFollowingMouse(Vector2 mousePos) {
        if (towerHandler.getPendingTower() != null && towerHandler.isBuyingState()) {
            towerHandler.getPendingTower().setPosition(mousePos);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemyHandler.addEnemy(enemy);
    }

    @Override
    public void onEnemyDeath(Enemy enemy) {
        int moneyGained = enemy.getMoney();
        resourceHandler.gainMoney(moneyGained);
        resourceHandler.updateMoneyResource();
    }

    public WaveGenerator getGenerator() {
        return generator;
    }


    public List<Menu> getMenus() {
        return menus;
    }

    public UpgradeHandler getUpgradeHandler() {
        return this.upgradeHandler;
    }

    public TowerHandler getTowerHandler() {
        return towerHandler;
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

    public void addButton(Button button){ inGameButtons.add(button);}
    public void removeButton(Button button){inGameButtons.remove(button);}


    public EnemyHandler getEnemyhandler() {
        return enemyHandler;
    }

    public InfoMenu getInfoMenu() {
        return this.infoMenu;
    }

    public UpgradeMenu getUpgradeMenu(){
        return this.upgradeMenu;
    }

    public TargetingStrategyToggleButton getTargetingToggleButton() {
        return targetingStrategyToggleButton;
    }
}
