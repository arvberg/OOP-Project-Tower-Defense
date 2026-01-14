package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.controller.buttonui.*;
import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.PathFactory;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.states.*;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.units.decorations.Core;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.ui.playerui.*;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.AttackListener;
import com.IONA.TowerDefense.model.units.interfaces.EnemyDeathListener;
import com.IONA.TowerDefense.model.units.interfaces.InputListener;
import com.IONA.TowerDefense.model.units.interfaces.TowerListener;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * The main model class for the Tower Defense game.
 * <p>
 * This class manages the game state, resources, towers, enemies, projectiles,
 * UI elements, and interactions between them. It acts as the core of the
 * game's Model in an MVC architecture.
 * <p>
 * It implements {@link EnemyDeathListener}, {@link AttackListener}, and
 * {@link TowerListener} to respond to in-game events.
 */

public class GameModel implements EnemyDeathListener, AttackListener, TowerListener, InputListener {

    /// --- Game States ---
    private GameState currentState;
    private GameState startState = new StartState(this);
    private GameState runningState = new RunningState(this);
    private GameState pausedState = new PauseState(this);
    private GameState gameOverState = new GameOverState(this);

    /// --- Important Handlers ---
    private final List<Tower> towers;
    private final TowerHandler towerHandler;
    private final UpgradeHandler upgradeHandler;
    private final ResourceHandler resourceHandler;
    private final AttackHandler attackHandler;
    private final EnemyHandler enemyHandler;
    private final ActionHandler actionHandler;
    private final TowerFactory towerFactory;

    private final List<InputListener> inputListeners = new ArrayList<>();


    /// --- Game objects ---
    private final List<Enemy> enemies;
    private final List<Projectile> projectiles;
    private final List<Decoration> decorations;
    private final WaveGenerator generator;
    private Path path;
    private final Decoration core;



    /// --- UI components ---
    private List<Button> inGameButtons;
    private List<Button> gameOverButtons;
    private List<Button> towerItemButtons;
    private List<Menu> menus;
    private final PlayButton playButton;
    private final ExitButton exitButton;
    private final SpeedUpButton speedUpButton;
    private final PauseButton pauseButton;
    private final RestartButton restartButton;
    private final CancelButton cancelButton;
    private final TargetingStrategyToggleButton targetingStrategyToggleButton;
    private final TowerMenu towerMenu;
    private final InfoMenu infoMenu;
    private final UpgradeMenu upgradeMenu;
    private String background;

    public GameModel() {

        this.towerMenu = new TowerMenu(12, 7.5f, this);
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
        this.path = PathFactory.examplePath2();

        this.resourceHandler = new ResourceHandler();

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
        this.speedUpButton = new SpeedUpButton(14.8f, 0.2f);
        this.pauseButton = new PauseButton(0f, 0f);
        this.restartButton = new RestartButton(5f, 3f);
        this.cancelButton = new CancelButton(towerMenu.getMenuPosition().x, towerMenu.getMenuPosition().y, towerMenu.getWidth(), towerMenu.getHeight());
        cancelButton.setVisible(false);
        this.targetingStrategyToggleButton = new TargetingStrategyToggleButton(5, 5, "");


        inGameButtons.add(playButton);
        inGameButtons.add(speedUpButton);
        inGameButtons.add(pauseButton);
        inGameButtons.add(cancelButton);
        gameOverButtons.add(restartButton);
        gameOverButtons.add(exitButton);
        towerMenu.createGridItems(towerItemButtons);


        // används för att inte kunna placera torn på menues.
        menus.add(towerMenu);
        menus.add(infoMenu);
        //menus.add(upgradeMenu);

        placeCore(core);
        updateButtonLayout();


        this.currentState = startState;
        currentState.enter();
    }

    /// Game State

    /**
     * Starts a new game or continues the next wave.
     * Sets the game state to running and spawns the next wave of enemies.
     */
    public void startGame() {

        if (generator.getWaveNr() == 6) {
            generator.setGameDiff(generator.getGameDiff() + 1);
            generator.resetWaves();
        }

        generator.SpawnNextWave();
        setState(getRunningState());
        updateButtonLayout();
    }


    /**
     * Exits the game application.
     */
    public void exitGame() {
        com.badlogic.gdx.Gdx.app.exit();
    }

    /**
     * Restarts the game.
     */
    public void restartGame() {
        currentState.restartGame();
    }

    /**
     * Toggles the game's speed (normal / fast-forward).
     */
    public void toggleSpeed() {
        HeartBeat.toggleSpeed();
    }

    /**
     * Updates the game logic. Delegates update calls to the current game state.
     */
    public void update() {
        // All logik styrs av state-klassen
        if (currentState != null) {
            currentState.update(HeartBeat.delta);
        }
    }

    /**
     * Sets the current game state, calling exit() on the old state and enter() on the new state.
     *
     * @param newState The new game state to switch to.
     */
    public void setState(GameState newState) {
        if (currentState != null) {
            currentState.exit();
        }

        currentState = newState;
        currentState.enter();
    }

    /**
     * Returns the current game state.
     *
     * @return the current {@link GameState}
     */
    public void togglePause() {
        currentState.toggle();
    }

    /// Core

    public void placeCore(Decoration core) {
        Segment last = path.getSegment(path.segmentCount() - 2);
        Vector2 end = last.getEnd();

        core.setPosition(new Vector2(
            end.x + 0.1f,
            end.y)
        );

        decorations.add(core);
    }

    /**
     * Handles damage to the core/base by overlapping enemies.
     */
    public void coreDamaged() {
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
    }

    /**
     * Handles an action triggered by a UI button.
     *
     * @param action the action to handle
     * @param sourceButton the button that triggered the action
     */

    public void addInputListener(InputListener l){
        inputListeners.add(l);
    }

    public void handleAction(GameAction action, Button sourceButton) {
        actionHandler.handleAction(action, sourceButton);
    }

    /**
     * Updates all enemies with the given delta time.
     *
     * @param delta time since last update
     */
    public void updateEnemies(float delta) {
        enemyHandler.updateEnemies(delta);
    }

    /**
     * Selects a tower at the given position.
     *
     * @param selectedPoint position to select tower
     */
    public void selectTower(Vector2 selectedPoint) {
        towerHandler.selectTower(selectedPoint);
    }

    /**
     * Deselects the currently selected tower.
     */
    public void deselectTower() {
        towerHandler.deselectTower();
    }


    /**
     * Places a tower at the given position and deducts money accordingly.
     *
     * @param selectedPoint the position to place the tower
     */

    public void placeTower(Vector2 selectedPoint) {
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

    /**
     * Buys a tower of the specified type.
     *
     * @param tower the type/name of the tower
     */

    public void buyTower(String tower) {
        towerHandler.buyTower(tower);
    }


    /**
     * Sells the specified tower and refunds money.
     *
     * @param tower the tower to sell
     */

    public void sellTower(Tower tower) {
        towerHandler.sellTower(tower);
        resourceHandler.gainMoney(tower.getValue());
        resourceHandler.updateMoneyResource();
    }

    public void cancelTowerBuy() {
        towerHandler.cancelBuy();
    }

    /**
     * Upgrades the specified tower with the given upgrade if affordable.
     *
     * @param tower the tower to upgrade
     * @param upgrade the upgrade to apply
     */
    public void upgradeTower(Tower tower, TowerUpgrade upgrade, UpgradeMenuItem item) {
        if (resourceHandler.getMoney() >= upgrade.getCost() && towerHandler.getSelectedTower() != null) {
            upgradeHandler.upgrade(tower, upgrade);
            resourceHandler.spendMoney(upgrade.getCost());
            resourceHandler.updateMoneyResource();
            tower.updateValue(upgrade.getCost());
            item.updateNextCost();
        }
    }

    /**
     * Returns the tower at the specified position, if any.
     *
     * @param pos position to check
     * @return the {@link Tower} at that position, or null if none
     */
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

    /**
     * Called when an enemy dies, granting money to the player.
     *
     * @param enemy the enemy that died
     */
    @Override
    public void onEnemyDeath(Enemy enemy) {
        int moneyGained = enemy.getMoney();
        resourceHandler.gainMoney(moneyGained);
        resourceHandler.updateMoneyResource();
    }

    public void addEnemy(Enemy enemy) {
        enemyHandler.addEnemy(enemy);
    }

    ///  UI

    /**
     * Makes the pending tower follow the mouse position.
     *
     * @param mousePos current mouse position
     */
    public void updateTowerFollowingMouse(Vector2 mousePos) {
        if (towerHandler.getPendingTower() != null && towerHandler.isBuyingState()) {
            towerHandler.getPendingTower().setPosition(mousePos);
            cancelButton.setVisible(true);
        }
        else{
            cancelButton.setVisible(false);
        }
    }

    public void updateHover(Vector2 mousePos){
        for(TowerMenuItem item: getTowerMenuItems()){
            if (item.isHovered(mousePos)) {
                notifyButtonHovered(item.getTowerType());
            }

        }
    }
    /**
     * Updates visibility of buttons based on current game state.
     */
    public void updateButtonLayout() {
        boolean running = currentState == runningState;

        playButton.setVisible(!running);
        speedUpButton.setVisible(running);
    }


        /// Getters and setters

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

    public void addButton(Button button) {
        inGameButtons.add(button);
    }

    public void removeButton(Button button) {
        inGameButtons.remove(button);
    }

    public EnemyHandler getEnemyhandler() {
        return enemyHandler;
    }

    public InfoMenu getInfoMenu() {
        return this.infoMenu;
    }

    public UpgradeMenu getUpgradeMenu() {
        return this.upgradeMenu;
    }

    public GameState getStartState() {
        return startState;
    }

    public GameState getRunningState() {
        return runningState;
    }

    public GameState getPausedState() {
        return pausedState;
    }

    public GameState getGameOverState() {
        return gameOverState;
    }

    public EnemyHandler getEnemyHandler() {
        return enemyHandler;
    }

    public void toggleTargetingStrategy() {
        towerHandler.toggleTargetingStrategy();
    }

    // Getters for all lists
    public List<Tower> getTowers() {
        return towers;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public List<Decoration> getDecor() {
        return decorations;
    }

    public List<Resource> getResources() {
        return resourceHandler.getResources();
    }

    public ResourceHandler getResourceHandler() {
        return resourceHandler;
    }


    public String getBackground() {
        return this.background;
    }

    public GameState getState() {
        return currentState;
    }


    public TowerMenu getTowerMenu() {
        return this.towerMenu;
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Tower getSelectedTower() {
        return towerHandler.getSelectedTower();
    }

    public boolean isTowerSelected() {
        return towerHandler.isTowerSelected();
    }

    public boolean isBuyingState() {
        return towerHandler.isBuyingState();
    }

    public Tower getPendingTower() {
        return towerHandler.getPendingTower();
    }

    public void setPendingTower(Tower tower) {
        towerHandler.setPendingTower(tower);
    }

    public List<Button> getInGameButtons() {
        return inGameButtons;
    }

    public List<Button> getTowerItemButtons() {
        return towerItemButtons;
    }

    public List<Button> getGameOverButtons() {
        return gameOverButtons;
    }

    public PlayButton getPlayButton() {
        return playButton;
    }

    public ExitButton getExitButton() {
        return exitButton;
    }

    public SpeedUpButton getSpeedUpButton() {
        return speedUpButton;
    }

    public PauseButton getPauseButton() {
        return pauseButton;
    }

    public RestartButton getRestartButton() {
        return restartButton;
    }

    public AttackHandler getAttackHandler() {
        return attackHandler;
    }

    public List<TowerMenuItem> getTowerMenuItems() {
        return towerMenu.items;
    }

    public List<Button> getUpgradeMenuItems() {
        return upgradeMenu.items;
    }

    public void notifyButtonHovered(String s){
        for(InputListener l: inputListeners){
            l.onButtonHovered(s);
        }
    }


}
