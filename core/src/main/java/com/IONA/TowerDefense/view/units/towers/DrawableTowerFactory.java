package com.IONA.TowerDefense.view.units.towers;


import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerBasic;


public final class DrawableTowerFactory {

    private DrawableTowerFactory(){}

    public static DrawableTower create(Tower tower){
        return switch (tower){
            case TowerBasic t -> new TowerBasicDrawer(t);

            default -> throw new IllegalStateException("Unexpected value: " + tower);
        };
    }

}
