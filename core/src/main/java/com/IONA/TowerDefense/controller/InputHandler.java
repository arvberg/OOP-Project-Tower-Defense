package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.GameModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseListener, MouseMotionListener {

    public enum InputState {
        SELECTION_STATE,
        BUYING_STATE
    }

    GameModel model;
    private InputState state = InputState.SELECTION_STATE;

    public InputHandler (GameModel model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point clickedPoint = mouseEvent.getPoint();
        if (state == InputState.SELECTION_STATE) {
            model.selectTower(clickedPoint);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Point releasePoint = mouseEvent.getPoint();
        if (state == InputState.BUYING_STATE) {
            model.placeTower(releasePoint);
            setState(InputState.SELECTION_STATE);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

    public void setState(InputState state) {
        this.state = state;
    }

}
