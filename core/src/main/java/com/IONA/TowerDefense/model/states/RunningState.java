package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;

public class RunningState implements GameState {
    private GameModel model;

    public RunningState(GameModel model) {
        this.model = model;
    }

    @Override
    public void enter() {
        model.getPlayButton().setVisible(false);
        model.getSpeedUpButton().setVisible(true);
    }

    @Override
    public void update(float delta) {
        // Uppdatera spelets logik
        model.updateEnemies(delta);
        model.coreDamaged();
        model.getAttackHandler().update(delta);
        model.getTowerMenu().update(delta);
        model.getInfoMenu().update(delta);

        // Kolla om wave är klar
        if (model.getGenerator().WaveCleared()) {
            model.getGenerator().WaveReward();
            model.setState(model.getStartState());
        }

        // byt till game over state
        if (model.getResourceHandler().getLives() <= 0) {
            model.setState(model.getGameOverState());
        }
    }


    @Override
    public void toggle() {
        model.setState(model.getPausedState());
    }

    @Override
    public void enemyDeath(Enemy enemy) {
        // Bara hantera döda fiender om vi är i running state
        int moneyGained = enemy.getMoney();
        model.getResourceHandler().gainMoney(moneyGained);
        model.getResourceHandler().updateMoneyResource();
    }


    @Override
    public void exit() {
        // inget just nu
    }
}
