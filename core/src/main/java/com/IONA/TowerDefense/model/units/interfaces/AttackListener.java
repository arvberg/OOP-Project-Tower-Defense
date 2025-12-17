package com.IONA.TowerDefense.model.units.interfaces;

public interface AttackListener {
    default void onProjectileFired() {
    }

    default void onPulseActivated() {
    }
}
