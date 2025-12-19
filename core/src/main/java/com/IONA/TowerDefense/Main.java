package com.IONA.TowerDefense;

import com.IONA.TowerDefense.controller.GameController;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.view.Draw;
import com.IONA.TowerDefense.view.ui.Fonts;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

/**
 * Main class for the Tower Defense game.
 * <p>
 * Extends ApplicationAdapter from LibGDX and serves as the entry point for the game.
 * Responsible for initializing the game model, view, and controller, handling the
 * game loop (input, logic, rendering), resizing the viewport, and disposing resources.
 */
public class Main extends ApplicationAdapter {
    public GameController controller;
    public static GameModel model;
    public Draw painter; // view

    /**
     * Called once when the application starts.
     * Initializes the game model, view (Draw), and controller.
     */
    @Override
    public void create() {
        model = new GameModel();
        painter = new Draw(model);
        painter.create();
        controller = new GameController(model, painter);

    }
    /**
     * Called every frame to update and render the game.
     * Updates delta time, handles input, updates game logic, and draws the scene.
     */
    @Override
    public void render() {
        HeartBeat.delta = Gdx.graphics.getDeltaTime() * HeartBeat.getSpeedMultiplier();
        input();    // All input endast
        logic();    // Uppdatera spelvärlden
        draw();     // Måla
    }

    @Override
    public void resize(int w, int h) {
        if (w <= 0 || h <= 0) return;
        painter.resize(w, h);
    }
    /**
     * Called when the application is closing.
     * Disposes of all resources used by the Draw view.
     */
    @Override
    public void dispose() {
        painter.dispose();
    }
    /**
     * Handles all user input by delegating to the GameController.
     */
    private void input() {
        controller.update();
    }
    /**
     * Updates the game state by delegating to the GameModel.
     */
    private void logic() {
        model.update();
    }

    /**
     * Renders the game by calling the Draw view.
     */
    private void draw() {
        painter.draw();
    }
}

