package com.IONA.TowerDefense.model.ui.playerui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

public class ResourceHP extends Resource {
    public ResourceHP(int hp, Vector2 position, float width, float height){
        super(hp, position, width, height);
        this.color = Color.RED;
    }
}
