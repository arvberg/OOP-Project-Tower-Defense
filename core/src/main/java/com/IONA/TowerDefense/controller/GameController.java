package com.IONA.TowerDefense.controller;

public class GameController {

    WaveGenerator generator;
    public GameController(String difficulty){

        generator = new WaveGenerator(difficulty);


    }

    // När vi klickar på t.ex. play så kallar vi på generator.SpawnNextWave().
}
