package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.view.GameView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;


public class GameController {

    private final GameModel model;
    private final GameView view;
    private final Vector2 mousePos = new Vector2();

    public GameController (GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    public void update() {
        handleMouseInput();
    }

    // handle all input
    private void handleMouseInput() {

        mousePos.set(Gdx.input.getX(), Gdx.input.getY());

        // Left-click: Select a tower
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            model.onLeftClick(mousePos.x, mousePos.y);
        }

        // Right-click: do something else
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            model.onRightClick(mousePos.x, mousePos.y);
        }

        // Optional: drag or move a selected object while holding left button
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            model.onMouseDrag(mousePos.x, mousePos.y);
        }
    }

}
