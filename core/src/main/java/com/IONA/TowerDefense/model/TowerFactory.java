package com.IONA.TowerDefense.model;

import com.badlogic.gdx.Game;

import java.awt.*;

public class TowerFactory {

    public static Tower createTower (String type, GameModel model) {

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
