package com.IONA.TowerDefense;

import com.IONA.TowerDefense.controller.GameController;
import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.audio.SoundManager;
import com.IONA.TowerDefense.model.audio.SoundPlayer;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.SpeedUpButton;
import com.IONA.TowerDefense.view.Draw;
import com.IONA.TowerDefense.view.ui.Fonts;
import com.IONA.TowerDefense.view.units.decorations.CoreDrawer;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;
import com.IONA.TowerDefense.view.units.projectiles.ProjectileBasicDrawer;
import com.IONA.TowerDefense.view.units.towers.TowerBasicDrawer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Main extends ApplicationAdapter {
    public GameController controller;
    public static GameModel model;
    public Draw painter; // view


    @Override
    public void create() {
        Fonts.load();
        model = new GameModel();
        painter = new Draw(model);
        painter.create();
        controller = new GameController(model, painter);

        SoundPlayer soundPlayer = new SoundPlayer(model.getSoundManager());

        model.addListener(soundPlayer);

    }

    @Override
    public void render() {
        HeartBeat.delta = Gdx.graphics.getDeltaTime() * model.getSpeedUpButton().getMultiplier();
        input();    // All input endast
        logic();    // Uppdatera spelvärlden
        draw();     // Måla
    }

    @Override
    public void resize(int w, int h) {
        if (w <= 0 || h <= 0) return;
        painter.resize(w, h);
    }

    @Override
    public void dispose() {
        painter.dispose();
    }

    private void input() {
        controller.update();
    }

    private void pauseInput() {
        controller.update();
    }

    private void logic() {
        model.update();
    }

    private void draw() {
        painter.draw();
    }
}

