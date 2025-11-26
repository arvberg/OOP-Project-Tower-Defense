package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.PlayButton;
import com.IONA.TowerDefense.model.ui.TowerMenuItem;
import com.IONA.TowerDefense.model.ui.TowerMenuToggleButton;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class InputHandler {

    private final PlayButton playButton;
    private final TowerMenuToggleButton towermenutogglebutton;
    private final List<TowerMenuItem> towerMenuItems;

    private GameModel model;

    public InputHandler (GameModel model) {

        this.playButton = model.getPlayButton();
        this.towermenutogglebutton = model.getTowerMenuToggleButton();
        this.towerMenuItems = model.getTowerMenuItems();
        this.model = model;
    }


    public void checkInput(Vector2 pos){
        towermenutogglebutton.isClicked(pos);
        playButton.isClicked(pos);
        for (TowerMenuItem t : towerMenuItems) {
            t.isClicked(pos);
        }
        //tower.isClicked(pos);
        // Om spelaren har ett torn redo att placera
        if (model.isBuyingState()) {
            if (clickedOnGameArea(pos)) {   // Se nedan
                model.placeTower(pos);
            }
        }
    }

    // Handle input for mouse hovering
    public void updateMouse(Vector2 worldMousePos) {
        model.updateTowerFollowingMouse(worldMousePos);
    }

    private boolean clickedOnGameArea(Vector2 pos) {
        for (Button b : model.getButtons()) {
            if (b.contains(pos.x, pos.y)) {
                return false;
            }
        }
        return true;
    }
}
