package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class UpgradeMenuToggleButton extends Button {

    private final UpgradeMenu upgrademenu;
    private final StateChanger schanger;
    private final SideMenu smenu;
    public Boolean isOpen;



    public UpgradeMenuToggleButton(float x, float y, UpgradeMenu menu, SideMenu smenu, StateChanger schanger) {

        super(x, y, 0.5f, 1.8f);
        this.upgrademenu = menu;
        this.schanger = schanger;
        this.smenu = smenu;
        this.isOpen = true;

    }

    public void changeState(){
        isOpen = !isOpen;
        upgrademenu.toggle();
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
        float newY = smenu.getMenuPosition().y + upgrademenu.getHeight() - this.height*2f;
        setButtonPosition(newX,newY);

    }

}
