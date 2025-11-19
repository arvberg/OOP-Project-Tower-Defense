package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.Main;

public class pauseButton extends Button{
    public pauseButton(float x, float y){
        super("Pause_button_temp_01.png", x+1, y, 1, 1);
    }

    @Override
    public void onClick(){
        Main.model.paused = !Main.model.paused;
        System.out.println("Paused: "+Main.model.paused);
    }

}
