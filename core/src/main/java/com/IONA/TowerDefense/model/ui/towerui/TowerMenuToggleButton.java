package com.IONA.TowerDefense.model.ui.towerui;

import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class TowerMenuToggleButton extends Button {

    private final TowerMenu towermenu;
    public final Texture texture1;
    public final Texture texture2;
    private Boolean isOpen;


    public TowerMenuToggleButton(float x, float y, TowerMenu menu) {

        super("Playbutton_temp_02.png", x, y, 0.5f, 0.5f);
        this.towermenu = menu;
        this.texture1 = this.texture;
        this.texture2 = new Texture("Pause_button_temp_01.png");
        this.isOpen = true;

    }

    @Override
    public void isClicked(Vector2 pos){
        if(bounds.contains(pos)){
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
        float newY = towermenu.getMenuPosition().y + towermenu.getHeight() - this.height;
        setButtonPosition(newX,newY);
        if(isOpen){
            this.texture = texture1;
        }
        else{
            this.texture = texture2;
        }
    }

}

