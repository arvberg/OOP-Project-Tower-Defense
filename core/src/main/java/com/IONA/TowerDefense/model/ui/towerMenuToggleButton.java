package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.graphics.Texture;

public class towerMenuToggleButton extends Button {

    private final towerMenu towermenu;
    private final Texture texture1;
    private final Texture texture2;
    private Boolean isOpen;


    public towerMenuToggleButton(float x, float y, towerMenu menu) {

        super("Playbutton_temp_02.png", x, y, 0.5f, 0.5f);
        this.towermenu = menu;
        this.texture1 = this.texture;
        this.texture2 = new Texture("Pause_button_temp_01.png");
        this.isOpen = true;

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
            isOpen = !isOpen;
    }

    public void updatePosition(){
        float newX = towermenu.getMenuPosition().x - this.width;
        float newY = towermenu.getMenuPosition().y + towermenu.height - this.height;
        setButtonPosition(newX,newY);
        if(isOpen){
            this.texture = texture1;
        }
        else{
            this.texture = texture2;
        }
    }

}

