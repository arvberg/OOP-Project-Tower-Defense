package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public class PlayButton extends Button {
    public WaveGenerator generator;

    //Rectangle bounds;
    public PlayButton(float x, float y, GameModel model) {
        super("Playbutton.png", x, y, 1, 1);
        this.generator = model.getGenerator();
        this.width = 1f;
        this.height = 1f;
        this.bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public void isClicked(Vector2 pos) {
        if (bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
        System.out.println("Start button pressed!");

        if (generator.getWaveNr() == 3) {
            generator.setGameDiff(generator.getGameDiff()+1);
            System.out.println(generator.getGameDiff());
            generator.resetWaves();
            generator.SpawnNextWave();
            System.out.println(generator.getWaveNr());
        }
        generator.SpawnNextWave();
        System.out.println(generator.getWaveNr());

        model.setGameState(GameState.RUNNING);
        System.out.println("State:" + model.getGameState());
        model.getPlayButton().toggleButton();

    }
}
