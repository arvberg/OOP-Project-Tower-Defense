package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.*;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    private final GameModel model;

    public InputHandler(GameModel model) {
        this.model = model;
    }

    // Hantera musklick
    public void checkInput(Vector2 pos) {
        model.getState().handleInput(model, pos);
    }

    // Hantera mushover
    public void updateMouse(Vector2 pos) {
        model.getState().handleHover(model, pos);
    }

    // Extra notifieringsmetoder
    /*public void notifyButtonClicked() {
        model.getState().onButtonClicked(model);
    }


    public void notifyInvalidClick() {
        model.getState().onInvalidClick(model);
    }
     */
}
