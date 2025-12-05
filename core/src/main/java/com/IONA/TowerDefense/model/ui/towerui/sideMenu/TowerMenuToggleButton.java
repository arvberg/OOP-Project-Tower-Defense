package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class TowerMenuToggleButton extends Button {

    private final TowerMenu towermenu;
    private final StateChanger schanger;
    private final SideMenu smenu;
    public final Texture texture1;
    public final Texture texture2;
    public Boolean isOpen;


    public TowerMenuToggleButton(float x, float y, TowerMenu menu, SideMenu smenu, StateChanger schanger) {

        super("Tower_Button_temp.png", x, y, 0.5f, 1.8f);
        this.towermenu = menu;
        this.smenu = smenu;
        this.schanger = schanger;
        this.texture1 = this.texture;
        this.texture2 = new Texture("Tower_button_selected_temp.png");
        this.isOpen = false;

    }

    public void changeState(){
        isOpen = !isOpen;
        towermenu.toggle();
    }

    @Override
    public void isClicked(Vector2 pos){
        if(bounds.contains(pos)){
            onClick();
        }
    }

    @Override
    public void onClick(){
            schanger.changeState();
    }

    public void updatePosition(){
        float newX = smenu.getMenuPosition().x - this.width;
        float newY = smenu.getMenuPosition().y + towermenu.getHeight() - this.height;
        setButtonPosition(newX,newY);
        if(isOpen){
            this.texture = texture1;
        }
        else{
            this.texture = texture2;
        }
    }

}

