package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.AttackHandler;
import com.IONA.TowerDefense.model.models.GameModel;

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
        model.getUpgradeMenu().update(HeartBeat.delta);
        model.getSideMenu().update(HeartBeat.delta);
        model.getTowerMenuToggleButton().updatePosition();
        model.getUpgradeMenuToggleButton().updatePosition();
        model.getSideMenuToggleButton().updatePosition();

        WaveGenerator wg = model.getPlayButton().generator;

        if (wg.WaveCleared()){
            wg.WaveReward();
            model.getPlayButton().toggleButton();
        }
    }
}
