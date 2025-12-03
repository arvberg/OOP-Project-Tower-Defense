package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.view.model.PathDrawer;
import com.IONA.TowerDefense.view.ui.*;
import com.IONA.TowerDefense.view.units.DecorationDrawer;
import com.IONA.TowerDefense.view.units.EnemyDrawer;
import com.IONA.TowerDefense.view.units.ProjectileDrawer;
import com.IONA.TowerDefense.view.units.TowerDrawer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

import static javax.swing.Spring.scale;

public class Draw {
    private final GameModel model;
    private SpriteBatch batch;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;
    private TextureAtlas atlas;
    private Animation<TextureAtlas.AtlasRegion> coreAnimation;
    private float stateTime = 0f;


    // Variables for fading transitions
    private float fadeTimer = 0f;
    private final float fadeDuration = 2f;

    public Draw(GameModel model) {this.model = model;}

    public void create() {

        batch = new SpriteBatch();
        viewport = new FitViewport(16,9);
        shapeRenderer = new ShapeRenderer();
        atlas = new TextureAtlas(Gdx.files.internal("atlas/core_animation.atlas"));
        coreAnimation = new Animation<>(0.01f, atlas.findRegions("Core_01"));
        coreAnimation.setPlayMode(Animation.PlayMode.LOOP);
        //coreSprite = new Sprite(coreAnimation.getKeyFrame(0));
        //coreSprite.setScale(1f);
        //coreSprite.setPosition(12,7);

    }

    public void resize(int w, int h) {
        viewport.update(w, h, true);
    }

    public Vector2 toWorld(float screenX, float screenY) {
        Vector3 v = new Vector3(screenX, screenY, 0);
        viewport.unproject(v);
        return new Vector2(v.x, v.y);
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

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion frame = coreAnimation.getKeyFrame(stateTime);
        List<Decoration> decorations = model.getDecor();
        DecorationDrawer.drawDecorations(decorations,batch, frame);

        TowerMenu towerMenu = model.getTowerMenu();
        TowerMenuDrawer.drawTowerMenu(towerMenu, batch);

        UpgradeMenu upgradeMenu = model.getUpgradeMenu();
        UpgradeMenuDrawer.drawUpgradeMenu(upgradeMenu, batch);

        List<Button> inGameButtons = model.getInGameButtons();
        ButtonDrawer.drawButtons(inGameButtons,batch);

        List<Resource> resources = model.getResources();
        ResourceDrawer.drawResources(resources,batch);

        List<Enemy> enemies = model.getEnemies();
        EnemyDrawer.drawEnemies(enemies,batch);

        List<Tower> towers = model.getTowers();
        TowerDrawer.drawTowers(towers, batch);

        if (model.isBuyingState() && model.getPendingTower() != null) {
            TowerDrawer.drawPendingTower(model.getPendingTower(), batch);
            TowerDrawer.drawRange(model.getPendingTower(), batch);
            if (model.overlaps(model.getPendingTower())) {
                batch.setColor(Color.RED);
                TowerDrawer.drawRange(model.getPendingTower(), batch);
                batch.setColor(Color.WHITE);
            }
        }

        if (model.isTowerSelected()) {
            TowerDrawer.drawRange(model.getSelectedTower(), batch);
        }

        List<Projectile> projectiles = model.getProjectiles();
        ProjectileDrawer.drawProjectiles(projectiles,batch);

        if (model.getGameState() == GameState.GAME_OVER) {
            List<Button> gameOverButtons = model.getGameOverButtons();

            fadeTimer += Gdx.graphics.getDeltaTime();
            float alpha = Math.min(fadeTimer / fadeDuration, 1f);
            batch.setColor(1f, 1f, 1f, alpha);
            batch.draw(model.getGameOverBackground(), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            ButtonDrawer.drawButtons(gameOverButtons,batch);
            batch.setColor(1f, 1f, 1f, 1f);
        }

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        HealthBarDrawer.drawHealthBar(enemies, shapeRenderer);
        shapeRenderer.end();

    }

    public void dispose() {
        if (batch != null) batch.dispose();
        if (shapeRenderer != null) shapeRenderer.dispose();

    }
}
