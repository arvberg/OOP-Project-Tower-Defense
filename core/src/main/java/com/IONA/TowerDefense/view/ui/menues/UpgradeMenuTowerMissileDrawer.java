package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.view.Assets;
import com.IONA.TowerDefense.view.ui.Fonts;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public final class UpgradeMenuTowerMissileDrawer implements DrawableMenu {

    private static final Texture TEXTURE = new Texture(Assets.MENU_UPGRADEMENU_TOWERMISSILE);

    private final UpgradeMenu upgradeMenu;
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final BitmapFont font1;
    private final BitmapFont font2;


    public UpgradeMenuTowerMissileDrawer(UpgradeMenu upgradeMenu) {
        this.upgradeMenu = upgradeMenu;
        this.font1 = Fonts.GOTHIC_FONT_DEFAULT_8;
        this.font2 = Fonts.GOTHIC_FONT_BOLD_4;
        this.x = upgradeMenu.getMenuPosition().x;
        this.y = upgradeMenu.getMenuPosition().y;
        this.height = upgradeMenu.getHeight();
        this.width = upgradeMenu.getWidth();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        if (upgradeMenu.towerIsClicked()) {
            batch.draw(TEXTURE, x, y, width, height);
            font1.setColor(0.859f, 0.824f, 0.773f, 1f);
            font1.draw(batch, "EDR", x + 0.2f, y + height - 0.2f);
            font2.setColor(0.859f, 0.824f, 0.773f, 1f);
            font2.draw(batch, "UPGRADES", x+0.2f,y+height-0.8f);
            font2.draw(batch, "TARGET" , x+0.2f, y+height-2.75f);
        }
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }

}

