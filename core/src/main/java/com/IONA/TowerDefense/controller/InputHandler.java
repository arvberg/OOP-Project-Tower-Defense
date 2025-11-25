package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.pauseButton;
import com.IONA.TowerDefense.model.ui.playButton;
import com.IONA.TowerDefense.model.ui.towerMenuToggleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.awt.event.MouseEvent;

public class InputHandler {

    public enum InputState {
        SELECTION_STATE,
        BUYING_STATE,
    }

    private GameModel model;
    private pauseButton pauseButton;
    private playButton playButton;
    private towerMenuToggleButton towermenutogglebutton;
    private InputState state = InputState.SELECTION_STATE;

    public InputHandler (GameModel model) {

        this.model = model;
        this.playButton = model.getPlayButton();
        this.pauseButton = model.getPauseButton();
        this.towermenutogglebutton = model.getTowerMenuToggleButton();

    }


    public void checkInput(float worldX, float worldY){

            towermenutogglebutton.isClicked(worldX,worldY);
            playButton.isClicked(worldX,worldY);

    }
}
