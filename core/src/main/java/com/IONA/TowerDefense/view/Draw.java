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
import com.IONA.TowerDefense.view.map.PathDrawer;
import com.IONA.TowerDefense.view.ui.*;
import com.IONA.TowerDefense.view.units.decorations.DrawableDecoration;
import com.IONA.TowerDefense.view.units.decorations.DrawableDecorationFactory;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemyFactory;
import com.IONA.TowerDefense.view.units.projectiles.DrawableProjectile;
import com.IONA.TowerDefense.view.units.projectiles.DrawableProjectileFactory;
import com.IONA.TowerDefense.view.units.towers.DrawableTower;
import com.IONA.TowerDefense.view.units.towers.DrawableTowerFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.IONA.TowerDefense.HeartBeat.delta;

public class Draw {
    private final GameModel model;
    private SpriteBatch batch;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;
    private TextureAtlas atlas;
    private Animation<TextureAtlas.AtlasRegion> coreAnimation;
    private final Map<Enemy, DrawableEnemy> enemyViews = new HashMap<>();
    private final Map<Tower, DrawableTower> towerViews = new HashMap<>();
    private final Map<Projectile, DrawableProjectile> projectileViews = new HashMap<>();
    private final Map<Decoration, DrawableDecoration> decorationViews = new HashMap<>();

    // Variables for fading transitions
    private float fadeTimer = 0f;
    private final float fadeDuration = 2f;

    public Draw(GameModel model) {this.model = model;}

    public void create() {

        batch = new SpriteBatch();
        viewport = new FitViewport(16,9);
        shapeRenderer = new ShapeRenderer();
    }

    public void resize(int w, int h) {
        viewport.update(w, h, true);
    }

    public Vector2 toWorld(float screenX, float screenY) {
        Vector3 v = new Vector3(screenX, screenY, 0);
        viewport.unproject(v);
        return new Vector2(v.x, v.y);
    }

    private DrawableEnemy getDrawableEnemy(Enemy e){
        return enemyViews.computeIfAbsent(e, DrawableEnemyFactory::create);
    }

    private DrawableTower getDrawableTower(Tower t){
        return towerViews.computeIfAbsent(t, DrawableTowerFactory::create);
    }

    private DrawableProjectile getDrawableProjectile(Projectile p){
        return projectileViews.computeIfAbsent(p, DrawableProjectileFactory::create);
    }

    private DrawableDecoration getDrawableDecoration(Decoration d){
        return decorationViews.computeIfAbsent(d, DrawableDecorationFactory::create);
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

        for(Decoration d: model.getDecor()){
            DrawableDecoration view = getDrawableDecoration(d);
            view.draw(batch, shapeRenderer, delta);
        }

        TowerMenu towerMenu = model.getTowerMenu();
        TowerMenuDrawer.drawTowerMenu(towerMenu, batch);

        UpgradeMenu upgradeMenu = model.getUpgradeMenu();
        UpgradeMenuDrawer.drawUpgradeMenu(upgradeMenu, batch);

        List<Button> inGameButtons = model.getInGameButtons();
        ButtonDrawer.drawButtons(inGameButtons,batch);

        List<Resource> resources = model.getResources();
        ResourceDrawer.drawResources(resources,batch);

        for (Enemy e : model.getEnemies()) {
            DrawableEnemy view = getDrawableEnemy(e);
            view.draw(batch, shapeRenderer, delta);
        }

        if (model.isBuyingState() && model.getPendingTower() != null) {
            Tower t = model.getPendingTower();
            DrawableTower view = getDrawableTower(t);
            view.drawPendingTower(batch);
            view.drawRange(batch);
            if (model.overlaps(model.getPendingTower())) {
                batch.setColor(Color.RED);
                view.drawRange(batch);
                batch.setColor(Color.WHITE);
            }
        }

        for(Tower t: model.getTowers()){
            DrawableTower view = getDrawableTower(t);
            view.draw(batch, shapeRenderer, delta);

            if (model.isTowerSelected() && model.getSelectedTower() == t) {
                view.drawRange(batch);
            }
        }

        for(Projectile p: model.getProjectiles()){
            DrawableProjectile view = getDrawableProjectile(p);
            view.draw(batch, shapeRenderer, delta);
        }

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
        List<Enemy> enemies = model.getEnemies();
        HealthBarDrawer.drawHealthBar(enemies, shapeRenderer);
        shapeRenderer.end();

        // Ta bort enemies och torn ifall de är döda/sålda.
        enemyViews.keySet().removeIf(e -> !model.getEnemies().contains(e));
        towerViews.keySet().removeIf(t -> !model.getTowers().contains(t));
        projectileViews.keySet().removeIf(p -> !model.getProjectiles().contains(p));
        decorationViews.keySet().removeIf(d -> !model.getDecor().contains(d));

    }

    public void dispose() {
        if (batch != null) batch.dispose();
        if (shapeRenderer != null) shapeRenderer.dispose();

    }
}
