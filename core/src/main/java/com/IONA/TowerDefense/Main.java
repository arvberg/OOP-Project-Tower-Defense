package com.IONA.TowerDefense;

import com.IONA.TowerDefense.controller.GameController;
import com.IONA.TowerDefense.controller.GameUpdater;
import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.Path;
import com.IONA.TowerDefense.model.Projectile;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.Vector;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont font;
    public GameController controller;
    public GameUpdater updater;
    public Draw painter;
    public GameModel model;
    public Path path;


    @Override
    public void create() {
        path = new Path();
        updater = new GameUpdater();
        model = new GameModel(path);
        painter = new Draw(model);
        controller = new GameController(model, painter);
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        font = new BitmapFont();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your application here. The parameters represent the new window size.
    }

    @Override
    public void render() {
        controller.update();
        updater.update();

        input();
        logic();
        draw();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        font.draw(batch,"Tower Defense", 140, 210);
        batch.end();
    }

    private void input() {

    }

    private void logic() {

    }

    private void draw() {

    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
