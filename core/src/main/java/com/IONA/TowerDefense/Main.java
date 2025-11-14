package com.IONA.TowerDefense;

import com.IONA.TowerDefense.controller.GameController;
import com.IONA.TowerDefense.controller.GameUpdater;
import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.ApplicationAdapter;

public class Main extends ApplicationAdapter {
    public GameController controller;
    public GameUpdater updater;
    public GameModel model;
    public Draw painter; // view

    @Override
    public void create() {
        model = new GameModel();
        updater = new GameUpdater();
        painter = new Draw(model);
        painter.create();
        controller = new GameController(model, painter);
    }

    @Override
    public void render() {
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

    private void input() { controller.update(); }
    private void logic() { updater.update(); }
    private void draw() {  painter.draw(); }
}

