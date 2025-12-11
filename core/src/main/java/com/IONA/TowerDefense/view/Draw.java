package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.audio.SoundManager;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.buttonui.RestartButton;
import com.IONA.TowerDefense.model.ui.buttonui.SellButton;
import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.view.map.BackgroundDrawer;
import com.IONA.TowerDefense.view.map.PathDrawer;
import com.IONA.TowerDefense.view.ui.*;
import com.IONA.TowerDefense.view.ui.buttons.*;
import com.IONA.TowerDefense.view.units.decorations.CoreDrawer;
import com.IONA.TowerDefense.view.units.decorations.DrawableDecoration;
import com.IONA.TowerDefense.view.units.decorations.DrawableDecorationFactory;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemyFactory;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;
import com.IONA.TowerDefense.view.units.projectiles.DrawableProjectile;
import com.IONA.TowerDefense.view.units.projectiles.DrawableProjectileFactory;
import com.IONA.TowerDefense.view.units.projectiles.ProjectileBasicDrawer;
import com.IONA.TowerDefense.view.units.towers.DrawableTower;
import com.IONA.TowerDefense.view.units.towers.DrawableTowerFactory;
import com.IONA.TowerDefense.view.units.towers.TowerBasicDrawer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
    private final Map<Enemy, DrawableEnemy> enemyViews = new HashMap<>();
    private final Map<Tower, DrawableTower> towerViews = new HashMap<>();
    private final Map<Projectile, DrawableProjectile> projectileViews = new HashMap<>();
    private final Map<Decoration, DrawableDecoration> decorationViews = new HashMap<>();
    private final Map<Button, DrawableButton> buttonViews = new HashMap<>();

    // Variables for fading transitions
    private float fadeTimer = 0f;
    private final float fadeDuration = 2f;

    private Texture gameOverTexture;

    public Draw(GameModel model) {this.model = model;}

    public void create() {

        batch = new SpriteBatch();
        viewport = new FitViewport(16,9);
        shapeRenderer = new ShapeRenderer();
        gameOverTexture = new Texture("Game_over_overlay_screen_01.png");

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

    private DrawableButton getDrawableButton(Button b){
        return buttonViews.computeIfAbsent(b, DrawableButtonFactory::create);
    }

    public void draw() {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        // Draw background
        batch.begin();
        BackgroundDrawer.drawBackground(batch, model.getBackground(),0,0, viewport.getWorldWidth(),viewport.getWorldHeight());
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

        for (Button b : model.getInGameButtons()){
            DrawableButton view = getDrawableButton(b);
            view.draw(batch, shapeRenderer, delta);
        }

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
            fadeTimer += Gdx.graphics.getDeltaTime();
            float alpha = Math.min(fadeTimer / fadeDuration, 1f);
            batch.setColor(1f, 1f, 1f, alpha);
            batch.draw(gameOverTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            for (Button b : model.getGameOverButtons()){
                DrawableButton view = getDrawableButton(b);
                view.draw(batch, shapeRenderer, delta);
            }
            batch.setColor(1f, 1f, 1f, 1f);
        }

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        List<Enemy> enemies = model.getEnemies();
        HealthBarDrawer.drawHealthBar(enemies, shapeRenderer);
        shapeRenderer.end();

        // Ta bort enemies och torn ifall de är döda/sålda.
        enemyViews.entrySet().removeIf(e -> !model.getEnemies().contains(e.getKey()));
        towerViews.entrySet().removeIf(t -> !model.getTowers().contains(t.getKey()));
        projectileViews.entrySet().removeIf(p -> !model.getProjectiles().contains(p.getKey()));
        decorationViews.entrySet().removeIf(d -> !model.getDecor().contains(d.getKey()));
        buttonViews.entrySet().removeIf(b -> !model.getInGameButtons().contains(b.getKey()));
        buttonViews.entrySet().removeIf(b -> !model.getGameOverButtons().contains(b.getKey()));

    }

    public void disposeDrawers() {
        TowerBasicDrawer.disposeStatic();
        EnemyBasicDrawer.disposeStatic();
        ProjectileBasicDrawer.disposeStatic();
        CoreDrawer.disposeStatic();
        PauseButtonDrawer.disposeStatic();
        PlayButtonDrawer.disposeStatic();
        RestartButtonDrawer.disposeStatic();
        SellButtonDrawer.disposeStatic();
        SideMenuToggleButtonDrawer.disposeStatic();
        SpeedUpButtonDrawer.disposeStatic();
        TowerMenuItemButtonDrawer.disposeStatic();
        TowerMenuItemButtonDrawer.disposeStatic();
        TowerMenuToggleButtonDrawer.disposeStatic();
        UpgradeMenuItemButtonDrawer.disposeStatic();
        UpgradeMenuToggleButtonDrawer.disposeStatic();
        // lägg till fler
    }

    public void dispose() {
        // Först disposa alla Drawer-resurser
        disposeDrawers();

        // Sedan dispose:a batch och shapeRenderer
        if (batch != null) batch.dispose();
        if (shapeRenderer != null) shapeRenderer.dispose();
        if (gameOverTexture != null) gameOverTexture.dispose();

        // Rensa mapparna
        towerViews.clear();
        enemyViews.clear();
        projectileViews.clear();
        decorationViews.clear();
        buttonViews.clear();
    }

}
