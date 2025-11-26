package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.AttackHandler;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.TowerMenuItem;
import com.IONA.TowerDefense.view.ui.ResourceDrawer;

import java.util.List;

public class GameUpdater  {

    private final GameModel model;

    private final AttackHandler attackHandler;

    public GameUpdater(GameModel model){
       this.model = model;
       this.attackHandler = model.getAttackHandler();
    }

    public void update(){
        if (model.paused){
            return;
        }
        //System.out.println("updating!");
        model.moveEnemies();
        model.coreDamaged();
        attackHandler.update();
        model.getTowerMenu().update(HeartBeat.delta);
        model.getTowerMenuToggleButton().updatePosition();

        //model.getPendingTower().updatePosition();
    }
}
