package com.IONA.TowerDefense.model;

import java.awt.*;

public class TowerFactory {

    public static Tower createTower (String type, Point pos, Dimension size) {

        if (type.equalsIgnoreCase("TowerBasic")) {
            return new TowerBasic(pos, size);
        }
        else if (type.equalsIgnoreCase("TowerFast")) {
            return new TowerFast(pos, size);
        }
        else {
            throw new IllegalArgumentException("Uknown tower");
        }

    }
}
