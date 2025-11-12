package com.IONA.TowerDefense.view;

import java.util.List;

public interface RenderDataProvider {
    List<RenderEnemy> getRenderEnemies();
    //List<RenderTower> getRenderTowers(); // om du inte har RenderTower ännu, kommentera denna rad så länge
}

