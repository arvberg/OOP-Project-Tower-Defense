package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SideMenuToggleButton extends Button {
    private final TowerMenu towermenu;
    private final UpgradeMenu upgrademenu;
    private final SideMenu sidemenu;
    private final StateChanger schanger;
    private Boolean isOpen;


    public SideMenuToggleButton(float x, float y, TowerMenu menu, UpgradeMenu umenu, SideMenu smenu, StateChanger schanger) {

        super(x, y, 0.5f, 1.8f);
        this.towermenu = menu;
        this.upgrademenu = umenu;
        this.sidemenu = smenu;
        this.schanger = schanger;
        this.isOpen = true;

    }

    @Override
    public void isClicked(Vector2 pos) {
        if (bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
    }

    public void updatePosition() {
        float newX = sidemenu.getMenuPosition().x - this.width;
        float newY = sidemenu.getMenuPosition().y + towermenu.getHeight() - this.height * 3f;
        setButtonPosition(newX, newY);

    }

}
