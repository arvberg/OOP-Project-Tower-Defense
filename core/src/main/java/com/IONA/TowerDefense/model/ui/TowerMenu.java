package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.GameModel;

import java.util.ArrayList;
import java.util.List;

import static com.IONA.TowerDefense.HeartBeat.delta;

public class TowerMenu extends Menu{

    private boolean open = true;

    private final float openX;
    private final float closedX;
    private float targetX;
    public List<TowerMenuItem> items;
    private GameModel model;

    float slideSpeed = 10f;

    public TowerMenu(float x, float y, GameModel model){
        super("SideBar.png", x, y, 3, 9);

        this.openX = x;
        this.targetX = x;
        this.closedX = x + width;
        this.model = model;
        this.items = new ArrayList<>();
    }

    public void toggle() {
        open = !open;
        if (open) {
            targetX = openX;
        } else {
            targetX = closedX;
        }
    }

    public void update(float delta) {
        float oldX;
        float newX;
        // om vi redan är nära target, sätt exakt
        if (Math.abs(menuPosition.x - targetX) < 0.02f) {
            oldX = menuPosition.x;
            menuPosition.x = targetX;
            newX = menuPosition.x;

            if(oldX<newX){moveItemPositive(oldX, newX);}
            else{moveItemNegative(oldX,newX);}

            bounds.setX(targetX);
            return;
        }
        // flytta menyn mot target
        if (menuPosition.x < targetX) {
            oldX = menuPosition.x;
            menuPosition.x += slideSpeed * HeartBeat.delta;
            newX = menuPosition.x;
            moveItemPositive(oldX,newX);

        } else {
            oldX = menuPosition.x;
            menuPosition.x -= slideSpeed * HeartBeat.delta;
            newX = menuPosition.x;
            moveItemNegative(oldX,newX);
        }
        bounds.setX(menuPosition.x);
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public void onClick(){
        //TODO
    }

    public void createGridItems(List<Button> buttons) {

        float startX = menuPosition.x + 0.2f;        // lite padding
        float startY = menuPosition.y + height - 1f; // börja vid toppen

        float itemSize = 1f;
        float spacing = 0.3f;

        int rows = 8;
        int cols = 2;

        int index = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                float x = startX + c * (itemSize + spacing);
                float y = startY - r * (itemSize + spacing);

                TowerMenuItem item = new TowerMenuItem(
                    "Tower_temp_03.png",
                    x,
                    y,
                    "TowerBasic",
                    this,
                    model
                );

                buttons.add(item);
                items.add(item);
            }
        }
    }

    public void moveItemNegative(float oldX, float newX){
        float diff = Math.abs(oldX-Math.abs(newX));
        for(TowerMenuItem item: items){
            item.setButtonPosition(item.getButtonPosition().x-diff,item.getButtonPosition().y);
        }
    }

    public void moveItemPositive(float oldX, float newX){
        float diff = Math.abs(oldX-Math.abs(newX));
        for(TowerMenuItem item: items){
            item.setButtonPosition(item.getButtonPosition().x+diff,item.getButtonPosition().y);
        }
    }

}
