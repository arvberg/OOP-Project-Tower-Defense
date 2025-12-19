package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.controller.buttonui.SellButton;

import java.util.ArrayList;
import java.util.List;


public class InfoMenu extends Menu {

    private boolean open = false;
    private boolean isHovered = false;

    private final float openX;
    private final float closedX;
    private float targetX;
    public List<Button> items;
    private GameModel model;

    float slideSpeed = 10f;

    public InfoMenu(float x, float y, GameModel model) {
        super(x, y, 2.8f, 3.7f);

        this.openX = x-width;
        this.targetX = x;
        this.closedX = x;
        this.model = model;
        this.items = new ArrayList<>();
    }

    public void toggle() {
    }

    @Override
    public void setMenuPosition(float x, float y){
        if(x + width > 16) {
            menuPosition.x = 16-width;
            menuPosition.y = y;
        }
        else{
            menuPosition.x = x;
            menuPosition.y = y;
        }
    }

    public void update(float delta) {
        bounds.setX(menuPosition.x);
    }

    public boolean isOpen() {
        return open;
    }
    public void setHoveredState(boolean b){ this.isHovered = b;}

    public boolean isHovered(){return this.isHovered;}

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
                        button = new SellButton(x, y);
                        break;
                    case 2:
                        button = new SellButton(x, y);
                        break;
                    case 3:
                        button = new SellButton(x, y);
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

