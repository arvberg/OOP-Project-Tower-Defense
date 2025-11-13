package com.IONA.TowerDefense.model;

import java.awt.*;

public class TowerFactory {

    public static Tower createTower (String type) {

        if (type.equalsIgnoreCase("TowerBasic")) {
            return new TowerBasic();
        }
        else if (type.equalsIgnoreCase("TowerFast")) {
            return new TowerFast();
        }
        else {
            throw new IllegalArgumentException("Unkown tower");
        }

    }
}
