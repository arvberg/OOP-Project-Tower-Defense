package com.IONA.TowerDefense.model.ui;

import com.IONA.TowerDefense.model.models.GameModel;

public class TowerMenu extends Menu{

    private boolean open = true;

    private final float openX;
    private final float closedX;
    private float targetX;

    public TowerMenu(float x, float y, GameModel model){
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
        float slideSpeed = 10f;
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

    public void createGridItems() {

        float startX = menuPosition.x + 0.2f;        // lite padding
        float startY = menuPosition.y + height - 1f; // börja vid toppen

        float itemSize = 1f;
        float spacing = 0.1f;

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
                    "BASIC"
                );

                // items.add(item);
            }
        }
    }

}
