package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.Menu;

import java.util.ArrayList;
import java.util.List;

public class TowerMenu extends Menu {

    public List<TowerMenuItem> items;
    private GameModel model;

    public TowerMenu(float x, float y, GameModel model) {
        super(x, y, 4, 1.5f);
        this.model = model;
        this.items = new ArrayList<>();
    }

    public void toggle() {
    }

    public void update(float delta) {
    }

    @Override
    public void onClick() {
        //TODO
    }

    public void createGridItems(List<Button> buttons) {

        int cols = 3;
        // spacing går att ändra.
        float spacing = 1.2f;

        float centerX = menuPosition.x + width / 2f;
        float step = spacing;
        float total = (cols - 1) * step;
        float startX = centerX - total / 2f;
        float y = menuPosition.y - 0.1f + height / 2f;

        for (int c = 0; c < cols; c++) {

            float x = startX + c * step;

            // Bestäm towerType här, exempelvis:
            String towerType;
            switch (c) {
                case 0:
                    towerType = "TowerBasic";
                    break;
                case 1:
                    towerType = "TowerPulse";
                    break;
                case 2:
                    towerType = "TowerBasic";
                    break;
                case 3:
                    towerType = "TowerBasic";
                    break;
                default:
                    towerType = "TowerBasic";
            }
            TowerMenuItem item = new TowerMenuItem(x, y, towerType, model);
            buttons.add(item);
            items.add(item);
        }
    }

}
