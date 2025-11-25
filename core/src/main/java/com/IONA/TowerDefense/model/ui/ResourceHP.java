package com.IONA.TowerDefense.model.ui;

import com.badlogic.gdx.math.Vector2;

public class ResourceHP {
    private int currentHP;

    private Vector2 position;
    private float width;
    private float height;

    private String hpBar;

    public ResourceHP(float x, float y, float width, float height){
        this.currentHP = currentHP;

        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;

        hpBar = new String(String.valueOf(this.currentHP));

    }
}
