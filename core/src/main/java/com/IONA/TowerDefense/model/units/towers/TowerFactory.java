package com.IONA.TowerDefense.model.units.towers;

public class TowerFactory {

    public static Tower createTower(String type) {

        if (type.equalsIgnoreCase("TowerBasic")) {
            return new TowerBasic();
        } else if (type.equalsIgnoreCase("TowerPulse")) {
            return new TowerPulse();
        } else if (type.equalsIgnoreCase("TowerMissile")) {
            return new TowerMissile();
        } else {
            throw new IllegalArgumentException("Unknown tower");
        }
    }
}
