package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.units.interfaces.UpgradeListener;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;

import java.util.ArrayList;
import java.util.List;

public class UpgradeHandler {

    private final List<UpgradeListener> listeners = new ArrayList<>();

    public UpgradeHandler() {
    }


    /**
     * Försöker applicera en upgrade på ett torn.
     */
    public void upgrade(Tower tower, TowerUpgrade upgrade) {
        notifyUpgradeListeners();
        upgrade.apply(tower);
    }

    public void notifyUpgradeListeners() {
        for (UpgradeListener u : listeners) {
            u.onUpgrade();
        }
    }

    public void addUpgradeListener(UpgradeListener listener) {
        listeners.add(listener);
    }
}
