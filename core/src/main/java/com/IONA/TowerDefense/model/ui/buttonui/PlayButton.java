package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.math.Vector2;

import static com.IONA.TowerDefense.Main.model;

public class PlayButton extends Button {
    public WaveGenerator generator;
    private boolean buttonLocked = false;
    private final float lockedX;

    //Rectangle bounds;
    public PlayButton(float x, float y, GameModel model) {
        super("Playbutton.png", x, y, 1, 1);
        this.generator = new WaveGenerator(model.getDifficulty(), model);
        this.lockedX = 500f;
        this.x = x;
        this.y = y;
        //this.bounds = new Rectangle(x,y,1,1);
    }

    public void togglePlayButton() {
        buttonLocked = !buttonLocked;
        if (buttonLocked) {
            model.getPlayButton().setButtonPosition(lockedX, y);
        } else {
            model.getPlayButton().setButtonPosition(x, y);
        }
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
        generator.SpawnNextWave();
        model.getPlayButton().togglePlayButton();
    }
}
