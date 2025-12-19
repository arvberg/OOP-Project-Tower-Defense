package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.units.towers.Tower;

public interface AttackListener {
    default void onProjectileFired(Tower tower) {
    }

    default void onPulseActivated(Tower tower) {
    }
}
