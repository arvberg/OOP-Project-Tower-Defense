package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.models.GameModel;

public class GameUpdater  {

    private GameModel model;

    public GameUpdater(GameModel model){
       this.model = model;
    }

    public void update(){
        if (model.paused){
            return;
        }
        //System.out.println("updating!");
        model.moveEnemies();
    }
};
