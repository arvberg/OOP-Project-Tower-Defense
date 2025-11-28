package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.Menu;

import java.util.ArrayList;
import java.util.List;

public class TowerMenu extends Menu {

    private boolean open = true;

    private final float openX;
    private final float closedX;
    private float targetX;
    public List<TowerMenuItem> items;
    private GameModel model;

    float slideSpeed = 10f;

    public TowerMenu(float x, float y, GameModel model) {
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
        if (Math.abs(menuPosition.x - targetX) < 0.1f) {
            oldX = menuPosition.x;
            menuPosition.x = targetX;
            newX = menuPosition.x;

            if (oldX < newX) {
                moveItemPositive(oldX, newX);
            } else {
                moveItemNegative(oldX, newX);
            }

            bounds.setX(targetX);
            return;
        }
        // flytta menyn mot target
        if (menuPosition.x < targetX) {
            oldX = menuPosition.x;
            menuPosition.x += slideSpeed * HeartBeat.delta;
            newX = menuPosition.x;
            moveItemPositive(oldX, newX);

        } else {
            oldX = menuPosition.x;
            menuPosition.x -= slideSpeed * HeartBeat.delta;
            newX = menuPosition.x;
            moveItemNegative(oldX, newX);
        }
        bounds.setX(menuPosition.x);
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public void onClick() {
        //TODO
    }

    public void createGridItems(List<Button> buttons) {

        float xLeft = menuPosition.x + width / 3.5f;
        float xRight = menuPosition.x + width - width / 3.5f;
        float yTop = menuPosition.y + height - height / 12f;
        float topSpacing = height / 5.2f;

        float x;
        float y;

        int rows = 5;
        int cols = 2;

        String texture = "";
        String towerType = "";

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (c == 0) {
                    x = xLeft;
                    y = yTop - r * topSpacing;
                } else {
                    x = xRight;
                    y = yTop - r * topSpacing;
                }

                if (c == 0 && r == 0) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 1 && r == 0) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 0 && r == 1) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 1 && r == 1) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 0 && r == 2) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 1 && r == 2) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 0 && r == 3) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 1 && r == 3) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 0 && r == 4) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                } else if (c == 1 && r == 4) {
                    texture = "Tower_temp_04.png";
                    towerType = "TowerBasic";
                }

                TowerMenuItem item = new TowerMenuItem(texture, x, y, towerType, model);
                buttons.add(item);
                items.add(item);

            }
        }
    }

    public void moveItemNegative(float oldX, float newX) {
        float diff = Math.abs(oldX - newX);
        for (TowerMenuItem item : items) {
            item.setButtonPosition(item.getButtonPosition().x - diff, item.getButtonPosition().y);
        }
    }

    public void moveItemPositive(float oldX, float newX) {
        float diff = Math.abs(oldX - newX);
        for (TowerMenuItem item : items) {
            item.setButtonPosition(item.getButtonPosition().x + diff, item.getButtonPosition().y);
        }
    }

}
