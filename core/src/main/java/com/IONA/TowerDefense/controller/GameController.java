package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.math.Vector2;

public class GameController {

    private final GameModel model;
    private final Draw view;
    private final Vector2 mousePos = new Vector2();

    private WaveGenerator waveGenerator;
    private InputHandler inputHandler;

    public GameController (GameModel model, Draw view) {
        this.model = model;
        this.view = view;
        this.inputHandler = new InputHandler(model);
    }

    public void update() {
       inputHandler.checkInput();
        // mer logik här
    }

    // handle all input


}
