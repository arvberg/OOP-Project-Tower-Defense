package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GameController {

    private final Draw view;
    private final InputHandler inputHandler;

    public GameController (GameModel model, Draw view) {
        this.view = view;
        this.inputHandler = new InputHandler(model);
    }

    public void update() {

            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.input.getY();
            // View konverterar till world-space
            Vector2 world = view.toWorld(mouseX, mouseY);

            inputHandler.updateMouse(world);

        if (Gdx.input.justTouched()) {
            inputHandler.checkInput(world);
        }

        // mer logik här
    }
    // handle all input
}
