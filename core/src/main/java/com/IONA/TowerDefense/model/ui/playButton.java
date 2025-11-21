package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.math.Rectangle;

public class playButton extends Button{
    WaveGenerator generator;
    Rectangle bounds;
    public playButton(float x, float y, GameModel model){
        super("Playbutton_temp_02.png", x, y, 1, 1);
        this.generator = new WaveGenerator(GameModel.difficulty, model);
        this.bounds = new Rectangle(x,y,1,1);
    }

    @Override
    public void isClicked(float x, float y){
        if(bounds.contains(x,y)){
            onClick();
        }
    }

    @Override
    public void onClick(){
        System.out.println("Start button pressed!");
        generator.SpawnNextWave();

    }
}
