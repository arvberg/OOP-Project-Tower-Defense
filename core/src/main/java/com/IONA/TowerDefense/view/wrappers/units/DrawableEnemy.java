package com.IONA.TowerDefense.view.wrappers.units;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.view.Drawable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class DrawableEnemy implements Drawable {

    protected final Enemy enemy;

    public DrawableEnemy(Enemy enemy){
        this.enemy = enemy;
    }

}
