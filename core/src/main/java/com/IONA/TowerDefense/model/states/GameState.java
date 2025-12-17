package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.badlogic.gdx.math.Vector2;

public interface GameState {

    default void enter() {
    };

    default void update(float delta) {
    };

    default void handleInput(Vector2 pos) {
    };

    default void handleHover(Vector2 mousePos) {
    };

    default void toggle() {
    };

    default void enemyDeath(Enemy enemy) {
    }

    default void restartGame() {
    }

    default void exit() {
    };

}
