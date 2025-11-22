package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class towerMenu extends Menu{

    private boolean open = true;

    private final float openX;
    private final float closedX;
    private float targetX;
    private final float slideSpeed = 10f;

    public towerMenu(float x, float y, GameModel model){
        super("SideBar.png", x, y, 3, 9);

        this.openX = x;
        this.targetX = x;
        this.closedX = x + width;
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
        // om vi redan är nära target, sätt exakt
        if (Math.abs(menuPosition.x - targetX) < 0.01f) {
            menuPosition.x = targetX;
            bounds.setX(targetX);
            return;
        }
        // flytta menyn mot target
        if (menuPosition.x < targetX) {
            menuPosition.x += slideSpeed * delta;
            if (menuPosition.x > targetX) menuPosition.x = targetX;
        } else {
            menuPosition.x -= slideSpeed * delta;
            if (menuPosition.x < targetX) menuPosition.x = targetX;
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
}
