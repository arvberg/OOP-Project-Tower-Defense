package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.*;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.utils.Timer;

public class WaveGenerator {

    GameController controller;
    Waves waves;
    int WaveNr;
    String GameDiff;

    public WaveGenerator(String difficulty) {
        //this.controller = new GameController();
        this.waves = Waves.load();
        this.WaveNr = 0;
        this.GameDiff = difficulty;
    }

    public void SpawnNextWave() {

        for (Waves.Enemy e : waves.waveslist.get(WaveNr).enemies) {


            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {

                    if (GameDiff.equals("e")) {

                        if(e.type.equals("1")){
                            //enemy = newType1Enemy("e")
                            //DrawClass.liveEnemies.add(enemy)
                        }

                        // Här tänker jag att vi lägger till fienden i en "liveEnemies" array som våran
                        // draw() metod hela tiden kommer kolla på, där fiendernas state finns.
                        // enemy = new enemy("e",) där strängen bestämmer attribut som hastighet, hp och delay.
                        // DrawClass.liveEnemies.add(enemy)

                    } else if (GameDiff.equals("m")) {

                    } else if (GameDiff.equals("h")) {

                    }


                }
            }, e.delay);

            WaveNr++;
        }

    }

}

