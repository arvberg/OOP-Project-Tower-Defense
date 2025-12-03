package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.GameState;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public class PauseButton extends Button {

    public PauseButton(float x, float y){
        super("Pause_button_temp_01.png", x+1, y, 1, 1);
    }

    @Override
    public void isClicked(Vector2 pos){
        if (bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick(){
        if (model.getGameState() == GameState.RUNNING){
            model.setGameState(GameState.PAUSED);
            System.out.println("Game Paused.");
        }
        else if (model.getGameState() == GameState.PAUSED){
            model.setGameState(GameState.RUNNING);
            System.out.println("Game Unpaused.");
        }
    }
}
