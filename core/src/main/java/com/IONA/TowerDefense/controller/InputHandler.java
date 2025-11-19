package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.pauseButton;
import com.IONA.TowerDefense.model.ui.playButton;
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
    private InputState state = InputState.SELECTION_STATE;

    public InputHandler (GameModel model) {

        this.model = model;
        this.playButton = model.getPlayButton();
        this.pauseButton = model.getPauseButton();

    }


    public void checkInput(){
        if (Gdx.input.justTouched()){
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.input.getY();

            float screenWidth = Gdx.graphics.getWidth();
            float screenHeight = Gdx.graphics.getHeight();

            // Gör om till våra libGDX koordinater (0-8, 0-5)
            float worldX = mouseX * (8f / screenWidth);
            float worldY = (screenHeight - mouseY) * (5f / screenHeight);

            playButton.isClicked(worldX,worldY);
        }
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        Point clickedPoint = mouseEvent.getPoint();
        Vector2 cp = (new Vector2(clickedPoint.x, clickedPoint.y));
        if (state == InputState.SELECTION_STATE) {
            model.selectTower(cp);
        }
    }


    public void mousePressed(MouseEvent mouseEvent) {
    }

    // Maybe not point and vector
    public void mouseReleased(MouseEvent mouseEvent) {
        Point releasePoint = mouseEvent.getPoint();
        Vector2 rp = (new Vector2(releasePoint.x, releasePoint.y));
        if (state == InputState.BUYING_STATE) {
            model.placeTower(rp);
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
