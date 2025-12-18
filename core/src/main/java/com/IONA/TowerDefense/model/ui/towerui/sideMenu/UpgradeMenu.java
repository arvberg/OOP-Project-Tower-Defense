package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.buttonui.SellButton;
import com.IONA.TowerDefense.model.ui.buttonui.TargetingStrategyToggleButton;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class UpgradeMenu extends Menu {

    private boolean open = false;
    private boolean isTowerClicked = false;
    private String belongsTo;

    private final float openX;
    private final float closedX;
    private float targetX;
    public List<Button> items;
    private GameModel model;


    float slideSpeed = 10f;

    public UpgradeMenu(float x, float y, GameModel model) {
        super(x, y, 3.2f, 4.5f);

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
        if(x < 0) {
            menuPosition.x = 0.2f;
        }
        else if(x+width>16){
            menuPosition.x = 16-width-0.2f;
        }
        else{
            menuPosition.x = x;
        }
        if(y+height > 9){
            menuPosition.y = y - 1.35f*height;
        }
        else{
            menuPosition.y = y;
        }
    }

    public void update(float delta) {
        bounds.setX(menuPosition.x);
    }

    public boolean isOpen() {
        return open;
    }

    public String getBelongsTo(){ return this.belongsTo; }

    public void setTowerIsClicked(boolean b, String towerType){
        this.isTowerClicked = b;
        if(b){
            this.belongsTo = towerType;
            if(!model.getMenus().contains(this)){
                model.getMenus().add(this);
            }
            System.out.println("Adding menu!");
            System.out.println(model.getMenus());
        }
        else{
            this.belongsTo = "";
            model.getMenus().remove(this);
            System.out.println("Removing Menu!");
            System.out.println(model.getMenus());
        }
    }

    public void setBelongsTo(String towerType){
        this.belongsTo = towerType;
    }

    public boolean towerIsClicked(){return this.isTowerClicked;}

    @Override
    public void onClick() {

        //TODO
    }

    public void createGridItems(Deque<TowerUpgrade> upgradePath1, Deque<TowerUpgrade> upgradePath2, Deque<TowerUpgrade> upgradePath3) {

        int rows = 5;
        int cols = 3;

        float xLeft  = menuPosition.x + width * 0.06f;
        float xRight = menuPosition.x + width * 0.94f;
        float xMid = menuPosition.x + width/2f;
        float yTop   = menuPosition.y + height * 0.65f;
        float yStep  = height * 0.12f;

        // 0 = tom, 1 = FireRate, 2 = Range, 3 = Sell
        int[][] layout = {
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {5, 0, 0}// Sell-knapp längst ner vänster
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int cellType = layout[r][c];
                if (cellType == 0) continue;  // ingen knapp på denna ruta

                float x;
                if (c == 0) {
                    x = xLeft;
                } else if (c == 1) {
                    x = xMid;
                } else {
                    x = xRight;
                }

                float y = yTop - r * yStep;

                Button button = null;

                System.out.println("x: " + x);

                switch (cellType) {
                    case 1:
                        button = new UpgradeMenuItem(x, y, upgradePath1, this);
                        break;
                    case 2:
                        button = new UpgradeMenuItem(x, y, upgradePath2, this);
                        break;
                    case 3:
                        button = new UpgradeMenuItem(x,y,upgradePath3, this);

                        break;
                    case 4:
                        button = new TargetingStrategyToggleButton(x,y);
                        break;
                    case 5:
                        button = new SellButton(x, y, this);
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

