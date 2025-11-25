package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.PlayButton;
import com.IONA.TowerDefense.model.ui.TowerMenuToggleButton;

public class InputHandler {

    public enum InputState {
        SELECTION_STATE,
        BUYING_STATE,
    }

    private final PlayButton playButton;
    private final TowerMenuToggleButton towermenutogglebutton;

    public InputHandler (GameModel model) {

        this.playButton = model.getPlayButton();
        this.towermenutogglebutton = model.getTowerMenuToggleButton();
    }


    public void checkInput(float worldX, float worldY){

            towermenutogglebutton.isClicked(worldX,worldY);
            playButton.isClicked(worldX,worldY);
    }
}
