package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.models.GameModel;

public class TowerMenuItem extends Button {

    private final String towerType; //

    public TowerMenuItem(String texture, float x, float y, String towerType) {
        super(texture, x, y, 1f, 1f);  // 1x1 world units
        this.towerType = towerType;
    }

    @Override
    public void onClick() {
        System.out.println("Clicked tower: " + towerType);
        // Här kommer vi senare kalla model.startBuying(towerType);
    }
}

