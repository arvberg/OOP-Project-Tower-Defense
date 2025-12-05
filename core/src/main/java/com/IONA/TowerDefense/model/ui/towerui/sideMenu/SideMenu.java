package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.ui.Menu;

public class SideMenu extends Menu {

    private boolean open = true;

    private final float openX;
    private final float closedX;
    private float targetX;

    float slideSpeed = 10f;

    public SideMenu(float x, float y) {
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

        if (Math.abs(menuPosition.x - targetX) < 0.1f) {

            menuPosition.x = targetX;
            bounds.setX(targetX);
            return;
        }
        // flytta menyn mot target
        if (menuPosition.x < targetX) {

            menuPosition.x += slideSpeed * delta;

        } else {

            menuPosition.x -= slideSpeed * delta;

        }
        bounds.setX(menuPosition.x);
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public void onClick() {
        //PLACDHOLDER
    }


}





