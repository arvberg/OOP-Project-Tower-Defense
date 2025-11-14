package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.Main;
import com.IONA.TowerDefense.controller.WaveGenerator;
import com.IONA.TowerDefense.model.GameModel;

public class playButton extends Button{
    WaveGenerator generator;
    public playButton(float x, float y){
        super("ProtTower.png", x, y, 1, 1);
        this.generator = new WaveGenerator(GameModel.difficulty, Main.model);
    }

    public void isClicked(float x, float y){
        if()
    }

    @Override
    public void onClick(){
        System.out.println("Start button pressed!");
        generator.SpawnNextWave();

    }
}
