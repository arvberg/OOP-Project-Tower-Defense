package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.Segment;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

public class Draw {
    private final GameModel model;
    private SpriteBatch batch;
    private FitViewport viewport;

    public Draw(GameModel model) {this.model = model;}

    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(16,9);
    }

    public void resize(int w, int h) {
        viewport.update(w, h, true);
    }

    public void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(model.getBackground(), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        for (Segment s: model.getPath().getSegments()){
            batch.draw(s.texture, s.getStartPosition().x, s.getStartPosition().y, 1, 1);
        }

        List<Button> buttons = model.getButtons();
        for (Button b: buttons){
            batch.draw(b.texture, b.getButtonPosition().x, b.getButtonPosition().y, b.width, b.height);
        }
        List<Enemy> enemies = model.getEnemies();
        for (Enemy e : enemies){
            Rectangle hb = e.getHitBox();
            batch.draw(e.texture, e.getCoor().x, e.getCoor().y, hb.width,hb.height);
        }

        batch.end();
    }

    public void dispose() {
        if (batch != null) batch.dispose();

    }
}
