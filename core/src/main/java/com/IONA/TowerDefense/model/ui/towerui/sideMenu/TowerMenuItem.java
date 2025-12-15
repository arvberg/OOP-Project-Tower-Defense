package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.badlogic.gdx.math.Vector2;

public class TowerMenuItem extends Button {

    private final String towerType; //
    private final GameModel model;

    public TowerMenuItem(float x, float y, String towerType, GameModel model) {
        super(x-.9f/2, y-.9f/2, .9f, 1.1f);  // 1x1 world units
        this.towerType = towerType;
        this.model = model;
    }

    @Override
    public void isClicked(Vector2 pos) {
        if(bounds.contains(pos)) {
            onClick();
        }
    }

    public boolean inBound(Vector2 pos){
        return bounds.contains(pos);
    }

    @Override
    public void onClick() {
        System.out.println("Clicked tower: " + towerType);
        model.buyTower(towerType);
    }


    public String getName() {
        return towerType;
    }
}

