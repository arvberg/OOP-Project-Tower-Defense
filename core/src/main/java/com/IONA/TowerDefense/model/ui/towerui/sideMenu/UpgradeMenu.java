package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.buttonui.SellButton;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;

import java.util.ArrayList;
import java.util.List;


public class UpgradeMenu extends Menu {

    private boolean open = false;

    private final float openX;
    private final float closedX;
    private float targetX;
    public List<Button> items;
    private GameModel model;

    float slideSpeed = 10f;

    public UpgradeMenu(float x, float y, GameModel model) {
        super("SideBar.png", x, y, 3, 9);

        this.openX = x-width;
        this.targetX = x;
        this.closedX = x;
        this.model = model;
        this.items = new ArrayList<>();
    }

    public void toggle() {
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
            menuPosition.x += slideSpeed * delta;
            newX = menuPosition.x;
            moveItemPositive(oldX, newX);

        } else {
            oldX = menuPosition.x;
            menuPosition.x -= slideSpeed * delta;
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

        int rows = 5;
        int cols = 2;

        float xLeft  = menuPosition.x + width / 3.5f;
        float xRight = menuPosition.x + width - width / 3.5f;
        float yTop   = menuPosition.y + height - height / 12f;
        float yStep  = height / 5.2f;

        // 0 = tom, 1 = FireRate, 2 = Range, 3 = Sell
        int[][] layout = {
            {1, 2},
            {0, 0},
            {0, 0},
            {0, 0},
            {3, 0}   // Sell-knapp längst ner vänster
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int cellType = layout[r][c];
                if (cellType == 0) continue;  // ingen knapp på denna ruta

                float x = (c == 0) ? xLeft : xRight;
                float y = yTop - r * yStep;

                Button button = null;

                switch (cellType) {
                    case 1:
                        button = new UpgradeMenuItem(x, y, model, new FireRateUpgrade(50));
                        break;
                    case 2:
                        button = new UpgradeMenuItem(x, y, model, new RangeUpgrade(50));
                        break;
                    case 3:
                        button = new SellButton(x, y, model);
                        break;
                }

                if (button != null) {
                    buttons.add(button);
                    items.add(button);
                }
            }
        }
    }

    private void moveItemNegative(float oldX, float newX) {
        float diff = Math.abs(oldX - newX);
        for (Button item : items) {
            item.setButtonPosition(item.getButtonPosition().x - diff, item.getButtonPosition().y);
        }
    }

    private void moveItemPositive(float oldX, float newX) {
        float diff = Math.abs(oldX - newX);
        for (Button item : items) {
            item.setButtonPosition(item.getButtonPosition().x + diff, item.getButtonPosition().y);
        }
    }



}

