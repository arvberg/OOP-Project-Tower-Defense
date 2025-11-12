package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.Enemy;
import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.view.GameView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.List;


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
    }

}
