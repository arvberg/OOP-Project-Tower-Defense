package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.badlogic.gdx.utils.Timer;

public class WaveGenerator {

    GameModel model;
    Waves waves;
    int WaveNr;
    int GameDiff;

    public WaveGenerator(int difficulty, GameModel model) {
        //this.controller = new GameController();
        this.waves = Waves.load();
        this.WaveNr = 0;
        this.GameDiff = difficulty;
        this.model = model;
    }

    public void SpawnNextWave() {
        float cumulativeDelay = 0;
        for (Waves.Enemy e : waves.waveslist.get(WaveNr).enemies) {
            cumulativeDelay += e.delay;  // 1, 2, 3, 4, ...

            float spawnTime = cumulativeDelay;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {


                            if (e.type.equals("1")) {
                                Enemy enemy = new EnemyBasic(GameDiff);
                                model.addEnemy(enemy);


                            }

                            // Här tänker jag att vi lägger till fienden i en "liveEnemies" array som våran
                            // draw() metod hela tiden kommer kolla på, där fiendernas state finns.
                            // enemy = new enemy("e",) där strängen bestämmer attribut som hastighet, hp och delay.
                            // DrawClass.liveEnemies.add(enemy)
                        }

            }, spawnTime);


        }

        WaveNr++;

    }

}

