package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.Main;
import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.ui.pauseButton;
import com.IONA.TowerDefense.model.ui.playButton;
import com.badlogic.gdx.Gdx;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler {

    public enum InputState {
        SELECTION_STATE,
        BUYING_STATE,
    }

    GameModel model;
    pauseButton pauseButton;
    playButton playButton;
    private InputState state = InputState.SELECTION_STATE;

    public InputHandler (GameModel model) {

        this.model = model;
        this.playButton = model.getPlayButton();
        this.pauseButton = model.getPauseButton();

    }


    public void checkInput(){
        if (Gdx.input.justTouched()){
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
            playButton.isClicked(mouseX,mouseY);
        }
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        Point clickedPoint = mouseEvent.getPoint();
        if (state == InputState.SELECTION_STATE) {
            model.selectTower(clickedPoint);
        }
    }


    public void mousePressed(MouseEvent mouseEvent) {
    }


    public void mouseReleased(MouseEvent mouseEvent) {
        Point releasePoint = mouseEvent.getPoint();
        if (state == InputState.BUYING_STATE) {
            model.placeTower(releasePoint);
            setState(InputState.SELECTION_STATE);
        }
    }


    public void mouseEntered(MouseEvent mouseEvent) {

    }


    public void mouseExited(MouseEvent mouseEvent) {

    }



    public void mouseMoved(MouseEvent mouseEvent) {
    }

    public void setState(InputState state) {
        this.state = state;
    }

}
