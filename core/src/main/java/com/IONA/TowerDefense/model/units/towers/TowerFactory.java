package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.models.GameModel;

public class TowerFactory {

    GameModel model;

    public static Tower createTower (String type) {

        if (type.equalsIgnoreCase("TowerBasic")) {
            return new TowerBasic();
        }
        else if (type.equalsIgnoreCase("TowerFast")) {
            return new TowerFast();
        }
        else {
            throw new IllegalArgumentException("Unknown tower");
        }
    }
}
