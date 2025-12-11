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

    float slideSpeed = 10f;

    public TowerMenu(float x, float y, GameModel model) {
        super("SideBar.png", x, y, 6, 1.5f);
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

        int cols = 4;      // fyra kolumner
        int rows = 1;      // en rad

        float spacingX = width / (cols + 1); // jämn horisontell fördelning
        float y = menuPosition.y + height / 2f; // centrera vertikalt

        for (int c = 0; c < cols; c++) {
            float x = menuPosition.x + spacingX * (c + 1);

            // Bestäm towerType här, exempelvis:
            String towerType;
            switch (c) {
                case 0:
                    towerType = "TowerBasic";
                    break;
                case 1:
                    towerType = "TowerBasic";
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
