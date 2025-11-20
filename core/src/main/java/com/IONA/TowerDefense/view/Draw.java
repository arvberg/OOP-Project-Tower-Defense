package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.Segment;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

public class Draw {
    private final GameModel model;
    private SpriteBatch batch;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;

    public Draw(GameModel model) {this.model = model;}

    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(16,9);
        shapeRenderer = new ShapeRenderer();
    }

    public void resize(int w, int h) {
        viewport.update(w, h, true);
    }

    public void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);



        batch.begin();

        batch.draw(model.getBackground(), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        float width = 0.6f;
        for (int i = 0; i < model.getPath().getSegments().size() - 1; i++) {
            Vector2 a = model.getPath().getSegment(i).getStartPosition();
            Vector2 b = model.getPath().getSegment(i).getEndForDraw(width);


            shapeRenderer.rectLine((a.x), (a.y), (b.x), (b.y),width);

        }
        shapeRenderer.end();

        batch.begin();
        List<Button> buttons = model.getButtons();
        for (Button b: buttons){
            batch.draw(b.texture, b.getButtonPosition().x, b.getButtonPosition().y, b.width, b.height);
        }
        List<Enemy> enemies = model.getEnemies();
        for (Enemy e : enemies){
            Rectangle hb = e.getHitBox();
            batch.draw(e.texture, hb.x, hb.y, hb.width, hb.height);

        }
/*
        List<Tower> towers = model.getTowers();
        for (Tower t : towers){
            Vector2 p = t.getPosition();
            batch.draw(t.texture, p.x, p.y, 0.5f, 0.5f);
        }

        List<Projectile> projectiles = model.getProjectiles();
        for (Projectile p : projectiles){
            Vector2 proj = p.getPosition();
            batch.draw(p.projectileIcon, proj.x, proj.y, 0.5f, 0.5f);
        }
*/





        batch.end();





    }

    public void dispose() {
        if (batch != null) batch.dispose();
        if (shapeRenderer != null) shapeRenderer.dispose();

    }
}
