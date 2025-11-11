package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.*;
import com.badlogic.gdx.controllers.Controller;


public class WaveGenerator {

    GameController controller;
    Waves waves;
    int WaveNr;
    String GameDiff;

    public WaveGenerator(String difficulty){
        //this.controller = new GameController();
        this.waves = Waves.load();
        this.WaveNr = 0;
        this.GameDiff = difficulty;
    }

    public void SpawnNextWave(){

        for(Waves.Enemy e : waves.waveslist.get(WaveNr).enemies) {

            if(e.difficulty.equals("e") && GameDiff.equals("e")){
                System.out.println("Spawnar enkel fiende!");
                // Här tänker jag att vi lägger till fienden i en "liveEnemies" array som våran
                // draw() metod hela tiden kommer kolla på, där fiendernas state finns.
                // enemy = new enemy("e", ... ) där strängen bestämmer attribut som hastighet, hp och delay.
                // DrawClass.liveEnemies.add(enemy)
            }
            else if(e.difficulty.equals("m") && GameDiff.equals("m")){
                System.out.println("Spawnar medelsvår fiende!");
            }
            else if(e.difficulty.equals("h") && GameDiff.equals("h")){
                System.out.println("Spawnar svår fiende!");
            }


        }

        WaveNr++;
    }


}
