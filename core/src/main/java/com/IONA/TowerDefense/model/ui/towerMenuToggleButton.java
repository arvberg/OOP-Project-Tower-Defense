package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.models.GameModel;

public class towerMenuToggleButton extends Button {

    private final towerMenu towermenu;

    public towerMenuToggleButton(float x, float y, GameModel model, towerMenu menu) {

        super("Playbutton_temp_02.png", x, y, 0.5f, 0.5f);
        this.towermenu = menu;

    }

    @Override
    public void isClicked(float x, float y){
        if(bounds.contains(x,y)){
            onClick();
        }
    }

    @Override
    public void onClick(){
            towermenu.toggle();
    }

    public void updatePosition(){
        float newX = towermenu.getMenuPosition().x - this.width;
        float newY = towermenu.getMenuPosition().y + towermenu.height - this.height;
        setButtonPosition(newX,newY);
    }

}

