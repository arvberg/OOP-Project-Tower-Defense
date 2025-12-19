package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.*;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GameController {

    private final Draw view;
    private final GameModel model;

    public GameController(GameModel model, Draw view) {
        this.view = view;
        this.model = model;

        AttackHandler attackhandler = model.getAttackHandler();
        EnemyHandler enemyhandler = model.getEnemyhandler();
        TowerHandler towerHandler = model.getTowerHandler();
        UpgradeHandler upgradeHandler = model.getUpgradeHandler();

        attackhandler.addAttackListener(model);
        attackhandler.addAttackListener(view);
        enemyhandler.addAttackListener(model);
        enemyhandler.addAttackListener(view);
        towerHandler.addTowerListener(model);
        towerHandler.addTowerListener(view);
        upgradeHandler.addUpgradeListener(view);

    }

    public void update() {

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();
        // View konverterar till world-space
        Vector2 world = view.toWorld(mouseX, mouseY);

        updateMouse(world);

        if (Gdx.input.justTouched()) {
            checkInput(world);
        }

        // mer logik här
    }

    // Hantera musklick
    public void checkInput(Vector2 pos) {
        model.getState().handleInput(model, pos);
    }

    // Hantera mushover
    public void updateMouse(Vector2 pos) {
        model.getState().handleHover(model, pos);
    }
}
