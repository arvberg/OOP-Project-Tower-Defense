package com.IONA.TowerDefense.model.ui.towerui;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.badlogic.gdx.math.Vector2;

public class TowerMenuItem extends Button {

    private final String towerType; //
    private final TowerMenu towermenu;
    private final GameModel model;

    public TowerMenuItem(String texture, float x, float y, String towerType, TowerMenu menu, GameModel model) {
        super(texture, x-.9f/2, y-.9f/2, .9f, .9f);  // 1x1 world units
        this.towerType = towerType;
        this.towermenu = menu;
        this.model = model;
    }

    @Override
    public void isClicked(Vector2 pos) {
        if(bounds.contains(pos)) {
            onClick();
        }
    }

    @Override
    public void onClick() {
        System.out.println("Clicked tower: " + towerType);
        model.buyTower(towerType);
    }


}

