package com.IONA.TowerDefense.model.ui.playerui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

public class ResourceMoney extends Resource {
    public ResourceMoney(int money, Vector2 position, float width, float height) {
        super(money, position, width, height);
        this.color = Color.ORANGE;
    }
}
