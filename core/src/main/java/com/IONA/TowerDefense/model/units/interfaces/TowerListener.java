package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.units.towers.Tower;

public interface TowerListener {
    void onTowerSelected();
    void onTowerPlaced();
    void onTowerSold();
    void onTowerDeselected();
    void onTowerPending();
}
