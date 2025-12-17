package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

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
    public void handleInput(Vector2 pos) {
        // Hantera in-game knappar
        for (Button b : model.getInGameButtons()) {
            if (b.isClicked(pos)) {
                model.handleAction(b.getAction(), b);
                return;
            }
        }

        for (TowerMenuItem item : model.getTowerMenuItems()) {
            if (item.isClicked(pos)) {
                model.handleAction(item.getAction(), item);
                return;
            }
        }

        if (model.isBuyingState() && clickedOnGameArea(pos)) {
            model.placeTower(pos);
            return;
        }

        // Torn-select
        if (clickedOnGameArea(pos)) {
            Tower clicked = model.getTowerAt(pos);
            if (clicked != null) {
                model.selectTower(pos);
            } else {
                model.deselectTower();
            }
        }
    }

    @Override
    public void handleHover(Vector2 mousePos) {
        model.updateTowerFollowingMouse(mousePos);
    }

    private boolean clickedOnGameArea(Vector2 pos) {
        for (Button b : model.getInGameButtons()) {
            if (b.contains(pos.x, pos.y)) return false;
        }
        for (var menu : model.getMenus()) {
            if (menu.contains(pos.x, pos.y)) return false;
        }
        return true;
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
