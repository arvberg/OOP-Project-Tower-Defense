package com.IONA.TowerDefense.controller;
import com.IONA.TowerDefense.view.RenderDataProvider;
import com.IONA.TowerDefense.view.RenderEnemy;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class GameUpdater implements RenderDataProvider {

    private final Array<RenderEnemy> renderEnemies = new Array<>();


    @Override
    public List<RenderEnemy> getRenderEnemies() {
        return Collections.unmodifiableList(Arrays.asList(renderEnemies.items));
    }

    // (valfritt senare) public Array<RenderEnemy> getRenderEnemiesMutable() { return renderEnemies; }

    // ge WaveGenerator tillgång att lägga till/ta bort
    Array<RenderEnemy> internalRenderEnemies() { return renderEnemies; }
}

