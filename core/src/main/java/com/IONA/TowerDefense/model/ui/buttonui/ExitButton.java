package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public class ExitButton extends Button {

    public ExitButton(float x, float y) {
        super( x, y, 1, 1);
        this.width = 1f;
        this.height = 1f;
        this.bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public void isClicked(Vector2 pos){
        if (bounds.contains(pos)){
            onClick();
        }
    }

    @Override
    public void onClick() {
        Gdx.app.exit();
    }

    @Override
    public void toggleButton(){
        if(model.getGameState() == GameState.GAME_OVER){
            model.getExitButton().setButtonPosition(10f,5f);
        }
        else{
            model.getExitButton().setButtonPosition(500f,5f);
        }
    }
}
