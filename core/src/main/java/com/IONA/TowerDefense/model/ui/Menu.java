package com.IONA.TowerDefense.model.ui;



import com.IONA.TowerDefense.model.models.RenderData;
import com.IONA.TowerDefense.model.units.interfaces.Renderable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Menu {
    public Vector2 menuPosition;
    public float width;
    public float height;
    protected Rectangle bounds;
    public Texture texture;

    public Menu(String texturePath, float x, float y, float width, float height){
        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.menuPosition = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Vector2 getMenuPosition(){
        return menuPosition;
    }

    public abstract void onClick();
}
