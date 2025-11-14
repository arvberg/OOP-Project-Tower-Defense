package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

public class Draw {
    private final GameModel model;
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont font;
    //private Texture BackgroundTexture;
    private FitViewport viewport;
    private Texture PlayButtonTexture;

    public Draw(GameModel model) {
        this.model = model;

    }

    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        font = new BitmapFont();
        PlayButtonTexture = new Texture("PlayButton.png");
        //this.BackgroundTexture = new Texture("ProtBackground.png");
        viewport = new FitViewport(8,5);
        // init camera/viewport här om du använder det
    }

    public void resize(int w, int h) {
        viewport.update(w, h, true);
        // uppdatera viewport/kamera här om du har en
    }

    public void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(model.getBackground(), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        List<Button> buttons = model.getButtons();
        for (Button b: buttons){
            batch.draw(b.texture, b.position.x, b.position.y, b.width, b.height);
        }

        // rita saker från modellen: enemies, towers, projektiler, UI ...
        // for (Enemy e : model.getEnemies()) { batch.draw(enemyTex, e.x, e.y); }

        batch.end();
    }

    public void dispose() {
        if (batch != null) batch.dispose();
        if (image != null) image.dispose();
        if (font != null) font.dispose();
        // dispose fler texturer här
    }
}
