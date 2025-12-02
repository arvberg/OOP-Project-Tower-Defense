package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.math.Vector2;

public class SpeedUpButton extends Button{

    private boolean fastForward;
    private int multiplier;

    public SpeedUpButton(float x, float y) {
        super("SpeedButton_temp_01.png", x, y, 1, 1);
        this.fastForward = false;
        this.multiplier = 1;
    }

    public int getMultiplier() {
        return this.multiplier;
    }

    @Override
    public void isClicked(Vector2 pos){
        if (bounds.contains(pos)){
            onClick();
        }
    }

    @Override
    public void onClick(){
        System.out.println("Speeding Up!");
        this.fastForward = !this.fastForward;
        if (fastForward){
            this.multiplier = 10;
        }
        else{
            this.multiplier = 1;
        }
    }
}
