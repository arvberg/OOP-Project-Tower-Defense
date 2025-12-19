package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.units.towers.Tower;

public interface TowerListener {

    default void onTowerStrategyToggle(String strategy){

    }

    default void onTowerSwitched(){
    }

    default void onTowerSelected() {
    }

    default void onTowerPlaced() {

    }

    default void onTowerSold() {

    }

    default void onTowerDeselected() {

    }

    default void onTowerPending() {

    }

    default void onCouldNotBuy() {

    }
}
