package com.IONA.TowerDefense.controller;
import com.IONA.TowerDefense.model.GameModel;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

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

