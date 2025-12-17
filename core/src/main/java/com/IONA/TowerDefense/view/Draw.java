package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;
import com.IONA.TowerDefense.model.units.towers.TowerPulse;
import com.IONA.TowerDefense.view.audio.SoundManager;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.*;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.view.map.BackgroundDrawer;
import com.IONA.TowerDefense.view.map.PathDrawer;
import com.IONA.TowerDefense.view.ui.*;
import com.IONA.TowerDefense.view.ui.buttons.*;
import com.IONA.TowerDefense.view.ui.menues.DrawableMenu;
import com.IONA.TowerDefense.view.ui.menues.DrawableMenuFactory;
import com.IONA.TowerDefense.view.ui.player.DrawableResource;
import com.IONA.TowerDefense.view.ui.player.DrawableResourceFactory;
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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.IONA.TowerDefense.HeartBeat.delta;

public class Draw implements EnemyDeathListener, AttackListener, InputListener, TowerListener, UpgradeListener {
    private final GameModel model;
    private SpriteBatch batch;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;
    private final Map<Enemy, DrawableEnemy> enemyViews = new HashMap<>();
    private final Map<Tower, DrawableTower> towerViews = new HashMap<>();
    private final Map<Projectile, DrawableProjectile> projectileViews = new HashMap<>();
    private final Map<Decoration, DrawableDecoration> decorationViews = new HashMap<>();
    private final Map<Button, DrawableButton> buttonViews = new HashMap<>();
    private final Map<Button, DrawableButton> itemViews = new HashMap<>();
    private final Map<Menu, DrawableMenu> menuViews = new HashMap<>();
    private final Map<Resource, DrawableResource> resourceViews = new HashMap<>();

    private final SoundManager soundManager = new SoundManager();

    private final List<AttackListener> attackListeners = new ArrayList<>();
    private final List<TowerListener> towerListeners = new ArrayList<>();

    // Variables for fading transitions
    private float fadeTimer = 0f;
    private final float fadeDuration = 2f;

    private Texture gameOverTexture;

    public Draw(GameModel model) {this.model = model;}

    public void create() {

        batch = new SpriteBatch();
        viewport = new FitViewport(16,9);
        shapeRenderer = new ShapeRenderer();
        gameOverTexture = new Texture(Assets.OVERLAY_GAMEOVER);
        Fonts.load();
        soundManager.load();
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

    private DrawableResource getDrawableResource(Resource r){
        return resourceViews.computeIfAbsent(r, DrawableResourceFactory::create);
    }

    private DrawableTower getDrawableTower(Tower t){
        DrawableTower view = towerViews.computeIfAbsent(t, DrawableTowerFactory::create);

        if (view instanceof AttackListener l && !attackListeners.contains(l)) {
            attackListeners.add(l);
        }

        return view;
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

    private DrawableMenu getDrawableMenu(Menu m){
        return menuViews.computeIfAbsent(m, DrawableMenuFactory::create);
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

        BitmapFont font = Fonts.GOTHIC_FONT;
        font.setColor(1,0,0,1);
        font.draw(batch, "TEST",5f,5f);

        for(Decoration d: model.getDecor()){
            DrawableDecoration view = getDrawableDecoration(d);
            view.draw(batch, shapeRenderer, delta);
        }

        for (Resource r: model.getResources()){
            DrawableResource view = getDrawableResource(r);
            view.draw(batch, shapeRenderer, delta);
        }

        //List<Resource> resources = model.getResources();
        //ResourceDrawer.drawResources(resources,batch);

        for (Enemy e : model.getEnemies()) {
            DrawableEnemy view = getDrawableEnemy(e);
            view.draw(batch, shapeRenderer, delta);
        }

        for (Menu m : model.getMenus()) {
            DrawableMenu view = getDrawableMenu(m);
                view.draw(batch, shapeRenderer, delta);
        }

        for (Button b : model.getInGameButtons()){
            DrawableButton view = getDrawableButton(b);
            view.draw(batch, shapeRenderer, delta);
        }

        if (!model.isBuyingState()) {
            for (Button b : model.getTowerItemButtons()) {
                DrawableButton view = getDrawableButton(b);
                view.draw(batch, shapeRenderer, delta);
            }
        }


        if (model.isBuyingState() && model.getPendingTower() != null) {
            Tower t = model.getPendingTower();
            Menu m = model.getTowerMenu();
            DrawableTower view = getDrawableTower(t);
            DrawableMenu menu = getDrawableMenu(m);
            view.drawPendingTower(batch);
            view.drawRange(batch);
            menu.drawCancelTower(batch);

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
        for (Enemy e : model.getEnemies()) {
            DrawableEnemy view = getDrawableEnemy(e);
            view.drawHealthBar(shapeRenderer, delta);
        }
        shapeRenderer.end();

        // Ta bort enemies och torn ifall de är döda/sålda.
        enemyViews.entrySet().removeIf(e -> !model.getEnemies().contains(e.getKey()));
        towerViews.entrySet().removeIf(t -> !model.getTowers().contains(t.getKey()));
        projectileViews.entrySet().removeIf(p -> !model.getProjectiles().contains(p.getKey()));
        decorationViews.entrySet().removeIf(d -> !model.getDecor().contains(d.getKey()));
        buttonViews.entrySet().removeIf(b -> !model.getInGameButtons().contains(b.getKey()));
        buttonViews.entrySet().removeIf(b -> !model.getGameOverButtons().contains(b.getKey()));
        itemViews.entrySet().removeIf(b -> !model.getTowerItemButtons().contains(b.getKey()));
        menuViews.entrySet().removeIf(m-> !model.getMenus().contains(m.getKey()));
        resourceViews.entrySet().removeIf(r -> !model.getResources().contains(r.getKey()));

    }

    public void disposeDrawers() {
        TowerBasicDrawer.disposeStatic();
        EnemyBasicDrawer.disposeStatic();
        ProjectileBasicDrawer.disposeStatic();
        CoreDrawer.disposeStatic();
        PauseButtonDrawer.disposeStatic();
        PlayButtonDrawer.disposeStatic();
        ExitButtonDrawer.disposeStatic();
        RestartButtonDrawer.disposeStatic();
        SellButtonDrawer.disposeStatic();
        SpeedUpButtonDrawer.disposeStatic();
        TowerMenuItemButtonDrawer.disposeStatic();
        TowerMenuItemButtonDrawer.disposeStatic();
        UpgradeMenuItemButtonDrawer.disposeStatic();

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

    @Override
    public void onProjectileFired(Tower tower) {
        soundManager.playSound("fire");
        for (AttackListener l : attackListeners) {
            l.onProjectileFired(tower);
        }
    }

    @Override
    public void onPulseActivated(Tower tower) {
        soundManager.playSound("pulse");
        for (AttackListener l : attackListeners) {
            l.onPulseActivated(tower);
        }
    }

    @Override
    public void onEnemyDeath(Enemy enemy) {
        soundManager.playSound("enemy_basic_death");
    }

    @Override
    public void onButtonClicked() {
        soundManager.playSound("ui_button_click");
    }

    @Override
    public void onTowerSelected() {
        soundManager.playSound("click_tower");
    }

    @Override
    public void onTowerPlaced() {
        soundManager.playSound("place_tower");
    }

    @Override
    public void onTowerSold() {
        soundManager.playSound("sell_tower");
    }


    @Override
    public void onCouldNotBuy() {
        soundManager.playSound("invalid_click");
    }

    @Override
    public void onUpgrade() {
        soundManager.playSound("tower_upgraded");
    }
}
