package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.models.AttackHandler;
import com.IONA.TowerDefense.model.models.GameModel;

public class GameUpdater  {

    private GameModel model;

    AttackHandler attackHandler;

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
    }
};
