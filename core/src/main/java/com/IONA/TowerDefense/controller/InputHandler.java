package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.PlayButton;
import com.IONA.TowerDefense.model.ui.TowerMenuItem;
import com.IONA.TowerDefense.model.ui.TowerMenuToggleButton;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class InputHandler {

    private final PlayButton playButton;
    private final TowerMenuToggleButton towermenutogglebutton;
    private final List<TowerMenuItem> towerMenuItems;
    private final List<Tower> towers;

    private GameModel model;

    public InputHandler (GameModel model) {

        this.playButton = model.getPlayButton();
        this.towermenutogglebutton = model.getTowerMenuToggleButton();
        this.towerMenuItems = model.getTowerMenuItems();
        this.model = model;
        this.towers = model.getTowers();
    }


    public void checkInput(Vector2 pos){
        // Först UI-kontroller
        towermenutogglebutton.isClicked(pos);
        playButton.isClicked(pos);
        for (TowerMenuItem t : towerMenuItems) {
            t.isClicked(pos);
        }

        // Om spelaren har ett torn redo att placera
        if (model.isBuyingState()) {
            if (clickedOnGameArea(pos)) {
                model.placeTower(pos);
            }// Tornet placeras
            return; // Avsluta, vi ska inte välja torn ännu
        }

        // Endast välj torn om man INTE håller på att placera ett nytt
        if (clickedOnGameArea(pos)) {
            model.selectTower(pos);
            if (model.isTowerSelected()) {
                Tower selected = model.getSelectedTower();
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
