package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.units.interfaces.UpgradeListener;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;

import java.util.ArrayList;
import java.util.List;
/**
 * Handles tower upgrades and notifies {@link UpgradeListener}s
 * when an upgrade is applied.
 */
public class UpgradeHandler {

    private final List<UpgradeListener> listeners = new ArrayList<>();

    public UpgradeHandler() {
    }

    /**
     * Applies the given upgrade to the specified tower and
     * notifies all registered upgrade listeners.
     *
     * @param tower   The tower to upgrade.
     * @param upgrade The upgrade to apply.
     */
    public void upgrade(Tower tower, TowerUpgrade upgrade) {
        notifyUpgradeListeners();
        upgrade.apply(tower);
    }

    /**
     * Notifies all registered upgrade listeners that an upgrade has been applied.
     */
    public void notifyUpgradeListeners() {
        for (UpgradeListener u : listeners) {
            u.onUpgrade();
        }
    }

    public void addUpgradeListener(UpgradeListener listener) {
        listeners.add(listener);
    }
}
