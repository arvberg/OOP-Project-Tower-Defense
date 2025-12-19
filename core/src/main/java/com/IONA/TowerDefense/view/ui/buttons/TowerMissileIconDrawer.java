package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.IONA.TowerDefense.model.units.interfaces.InputListener;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class TowerMissileIconDrawer implements DrawableButton, InputListener {

    private final TowerMenuItem tower;
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;
    private boolean showMenu = false;

    // STATIC TEXTURE shared by all instances
    private static final Texture TEXTURE = new Texture(Assets.BUTTON_TOWERICON_TOWERMISSILE);
    private static final Texture TEXTURE_MENU = new Texture(Assets.MENU_INFOMENU_TOWERMISSILE);

    public TowerMissileIconDrawer(TowerMenuItem tower) {
        this.tower = tower;
        this.dimensionX = tower.getWidth();
        this.dimensionY = tower.getHeight();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        p = tower.getButtonPosition();
        batch.draw(TEXTURE, p.x, p.y, dimensionX, dimensionY);
        if(showMenu){
            float menuWidth = 3.5f;
            float menuHeight = 3.7087f;
            batch.draw(TEXTURE_MENU, 16-menuWidth-0.1f, p.y - menuHeight, menuWidth, menuHeight);
        }
        showMenu = false;
    }

    @Override
    public void onButtonHovered(String s){
        if(s == tower.getTowerType()){
            this.showMenu = true;
        }
    }

    public static void disposeStatic() {
        if (TEXTURE != null) TEXTURE.dispose();
    }
}
