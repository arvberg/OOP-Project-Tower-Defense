package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;

import java.util.ArrayList;
import java.util.List;


public class InfoMenu extends Menu {

    private boolean isHovered = false;

    private final float openX;
    private final float closedX;
    private float targetX;
    public List<Button> items;
    private GameModel model;

    public InfoMenu(float x, float y, GameModel model) {
        super(x, y, 2.8f, 3.7f);
        this.model = model;
        this.openX = x - width;
        this.targetX = x;
        this.closedX = x;
        this.items = new ArrayList<>();
    }


    @Override
    public void setMenuPosition(float x, float y) {
        if (x + width > 16) {
            menuPosition.x = 16 - width;
            menuPosition.y = y;
        } else {
            menuPosition.x = x;
            menuPosition.y = y;
        }
    }

    public void update(float delta) {
        bounds.setX(menuPosition.x);
    }

    public boolean isHovered() {
        return this.isHovered;
    }


}

