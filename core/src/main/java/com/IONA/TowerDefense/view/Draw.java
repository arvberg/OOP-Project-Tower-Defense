package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.view.model.PathDrawer;
import com.IONA.TowerDefense.view.ui.ButtonDrawer;
import com.IONA.TowerDefense.view.units.DecorationDrawer;
import com.IONA.TowerDefense.view.units.EnemyDrawer;
import com.IONA.TowerDefense.view.units.ProjectileDrawer;
import com.IONA.TowerDefense.view.units.TowerDrawer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

        // Draw background
        batch.begin();
        batch.draw(model.getBackground(), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.end();

        // Draw Path
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        PathDrawer.drawPath(shapeRenderer);
        shapeRenderer.end();

        batch.begin();

        List<Button> buttons = model.getButtons();
        ButtonDrawer.drawButtons(buttons,batch);

        List<Decoration> decorations = model.getDecor();
        DecorationDrawer.drawDecorations(decorations,batch);

        List<Enemy> enemies = model.getEnemies();
        EnemyDrawer.drawEnemies(enemies,batch);

        List<Tower> towers = model.getTowers();
        TowerDrawer.drawTowers(towers, batch);

        List<Projectile> projectiles = model.getProjectiles();
        ProjectileDrawer.drawProjectiles(projectiles,batch);

        batch.end();


    }

    public void dispose() {
        if (batch != null) batch.dispose();
        if (shapeRenderer != null) shapeRenderer.dispose();

    }
}
