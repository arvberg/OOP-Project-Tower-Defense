package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.GameModel;

public class TowerFactory {

    public Tower createTower (String type, GameModel model) {

        if (type.equalsIgnoreCase("TowerBasic")) {
            return new TowerBasic(model);
        }
        else if (type.equalsIgnoreCase("TowerFast")) {
            return new TowerFast(model);
        }
        else {
            throw new IllegalArgumentException("Unkown tower");
        }
    }
}
