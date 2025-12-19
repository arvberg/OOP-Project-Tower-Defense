package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.model.units.enemies.EnemyFast;
import com.IONA.TowerDefense.model.units.enemies.EnemyTanky;
import com.badlogic.gdx.utils.Timer;
/**
 * WaveGenerator handles spawning and managing enemy waves in the game.
 *
 * Responsibilities:
 * - Spawn enemies for the current wave with delays based on the Waves configuration.
 * - Track when a wave has finished spawning and when it is cleared.
 * - Grant rewards to the player after clearing a wave.
 * - Reset wave progression for a new game.
 *
 * Fields:
 * - model: reference to GameModel.
 * - waves: predefined waves and enemy sequences.
 * - WaveNr: current wave index.
 * - GameDiff: current game difficulty.
 */
public class WaveGenerator {

    private boolean finishedSpawning;
    private boolean rewardGiven = false;

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
        finishedSpawning = false;
        rewardGiven = false;

        System.out.println("WAVENR:" + WaveNr);
        float cumulativeDelay = 0;

        for (Waves.Enemy e : waves.waveslist.get(WaveNr).enemies) {
            cumulativeDelay += e.getDelay();  // 1, 2, 3, 4, ...

            float spawnTime = cumulativeDelay;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (model.getState() == model.getRunningState()) {
                        if (e.getEnemyType().equals("1")) {
                            Enemy enemy = new EnemyBasic(GameDiff);
                            model.addEnemy(enemy);
                            System.out.println(enemy.getHp());
                        }
                        if (e.getEnemyType().equals("2")) {
                            Enemy enemy = new EnemyFast(GameDiff);
                            model.addEnemy(enemy);
                        }
                        if (e.getEnemyType().equals("3")) {
                            Enemy enemy = new EnemyTanky(GameDiff);
                            model.addEnemy(enemy);
                        }
                    } else {
                        // Reschedule task eller avbryt spawn
                    }
                }
            }, spawnTime);
        }
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                finishedSpawning = true;
            }
        }, cumulativeDelay);

        WaveNr++;

    }

    public boolean WaveCleared() {
        if (model.getState() != model.getGameOverState()) {
            return finishedSpawning &&
                !rewardGiven &&
                model.getEnemies().isEmpty();
        }
        return false;
    }

    public void WaveReward() {
        int moneyReward = 100 + 50 * WaveNr;

        model.getResourceHandler().gainMoney(moneyReward);
        model.getResourceHandler().updateMoneyResource();

        rewardGiven = true;
    }

    public void resetWaves() {
        WaveNr = 0;
        finishedSpawning = false;
        rewardGiven = false;
    }

    public int getWaveNr() {
        return this.WaveNr;
    }

    public int getGameDiff() {
        return this.GameDiff;
    }

    public void setGameDiff(int diff) {
        this.GameDiff = diff;
    }
}

