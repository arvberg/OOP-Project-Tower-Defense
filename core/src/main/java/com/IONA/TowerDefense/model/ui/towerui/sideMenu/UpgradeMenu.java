package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.buttonui.SellButton;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class UpgradeMenu extends Menu {

    private boolean open = false;
    private boolean isTowerClicked = false;

    private final float openX;
    private final float closedX;
    private float targetX;
    public List<Button> items;
    private GameModel model;

    float slideSpeed = 10f;

    public UpgradeMenu(float x, float y, GameModel model) {
        super(x, y, 3, 2.5f);

        this.openX = x-width;
        this.targetX = x;
        this.closedX = x;
        this.model = model;
        this.items = new ArrayList<>();
    }

    public void clearGridItems(){
        for(Button b: items){
            model.removeButton(b);
        }
        items.clear();
        }



    public void toggle() {
    }

    @Override
    public void setMenuPosition(float x, float y){
        if(x + width > 16) {
            this.menuPosition.x = 16-width;
            this.menuPosition.y = y;
        }
        else{
            this.menuPosition.x = x;
            this.menuPosition.y = y;
        }
    }

    public void update(float delta) {
        bounds.setX(menuPosition.x);
    }

    public boolean isOpen() {
        return open;
    }
    public void setTowerIsClicked(boolean b){ this.isTowerClicked = b;}

    public boolean towerIsClicked(){return this.isTowerClicked;}

    @Override
    public void onClick() {

        //TODO
    }

    public void createGridItems(Deque<TowerUpgrade> upgradePath1, Deque<TowerUpgrade> upgradePath2) {

        int rows = 3;
        int cols = 2;

        float xLeft  = menuPosition.x + width * 0.2f;
        float xRight = menuPosition.x + width * 0.8f;
        float yTop   = menuPosition.y + height * 0.8f;
        float yStep  = height * 0.26f;

        // 0 = tom, 1 = FireRate, 2 = Range, 3 = Sell
        int[][] layout = {
            {1, 0},
            {2, 0},
            {3, 0}// Sell-knapp längst ner vänster
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int cellType = layout[r][c];
                if (cellType == 0) continue;  // ingen knapp på denna ruta

                float x = (c == 0) ? xLeft : xRight;
                float y = yTop - r * yStep;

                Button button = null;

                System.out.println("x: " + x);

                switch (cellType) {
                    case 1:
                        button = new UpgradeMenuItem(x, y, model, upgradePath1);
                        break;
                    case 2:
                        button = new UpgradeMenuItem(x, y, model, upgradePath2);
                        break;
                    case 3:
                        button = new SellButton(x, y, model);
                        break;
                }

                if (button != null) {
                    model.addButton(button);
                    items.add(button);
                }
            }
        }
    }







}

