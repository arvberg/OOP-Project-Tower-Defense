package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.*;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


/**
 * The main controller in the MVC pattern for the Tower Defense game.
 * <p>
 * Handles input from the player (mouse clicks and hover), updates the game model,
 * and communicates changes to the view.
 */
public class GameController {

    private final Draw view;
    private final GameModel model;

    /**
     * Initializes the controller and sets up event listeners between the model and view.
     */
    public GameController(GameModel model, Draw view) {
        this.view = view;
        this.model = model;

        AttackHandler attackhandler = model.getAttackHandler();
        EnemyHandler enemyhandler = model.getEnemyhandler();
        TowerHandler towerHandler = model.getTowerHandler();
        UpgradeHandler upgradeHandler = model.getUpgradeHandler();

        attackhandler.addAttackListener(model);
        attackhandler.addAttackListener(view);
        enemyhandler.addEnemyDeathListener(model);
        enemyhandler.addEnemyDeathListener(view);
        towerHandler.addTowerListener(model);
        towerHandler.addTowerListener(view);
        upgradeHandler.addUpgradeListener(view);

        model.addInputListener(view);

    }

    /**
     * Main update loop called each frame.
     * Converts mouse position, handles hover and clicks, and updates game logic.
     */
    public void update() {

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();
        // View konverterar till world-space
        Vector2 world = view.toWorld(mouseX, mouseY);

        updateMouse(world);

        if (Gdx.input.justTouched()) {
            checkInput(world);
        }
    }
    /**
     * Handles mouse clicks by delegating to the current game state.
     */
    public void checkInput(Vector2 pos) {
        model.getState().handleInput(model, pos);
    }


    /**
     * Handles mouse movement/hover by delegating to the current game state.
     */
    public void updateMouse(Vector2 pos) {
        model.getState().handleHover(model, pos);
    }
}
