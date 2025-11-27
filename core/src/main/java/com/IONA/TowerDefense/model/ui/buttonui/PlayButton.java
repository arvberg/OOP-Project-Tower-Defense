package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.math.Vector2;

public class PlayButton extends Button {
    WaveGenerator generator;
    //Rectangle bounds;
    public PlayButton(float x, float y, GameModel model){
        super("Playbutton_temp_02.png", x, y, 1, 1);
        this.generator = new WaveGenerator(model.getDifficulty(), model);
        //this.bounds = new Rectangle(x,y,1,1);
    }

    @Override
    public void isClicked(Vector2 pos){
        if(bounds.contains(pos)){
            onClick();
        }
    }

    @Override
    public void onClick(){
        System.out.println("Start button pressed!");
        generator.SpawnNextWave();
    }
}
