package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class MoneyDrawer implements DrawableResource{

    Vector2 p;

    public MoneyDrawer(ResourceMoney money){
        this.p = money.getPosition();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){



    }
}
