package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.Waves;
import com.IONA.TowerDefense.controller.WaveGenerator;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.units.towers.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

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
        handleMouseInput();
        // mer logik här
    }

    // handle all input
    private void handleMouseInput() {
        inputHandler.checkInput();
    }

    public void startWave() {
        waveGenerator.SpawnNextWave();
    }

}
