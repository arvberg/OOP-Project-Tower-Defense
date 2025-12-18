package com.IONA.TowerDefense.model.ui.playerui;

import com.IONA.TowerDefense.view.ui.Fonts;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

public class Resource {
    private int currentResource;
    protected Vector2 position;
    protected float width;
    protected float height;
    public String textBar;
    public Color color;
    public BitmapFont font;

    public Resource(int currentResource, Vector2 position, float width, float height){
        this.currentResource = currentResource;
        this.position = new Vector2(position);
        this.width = width;
        this.height = height;
        //this.font = Fonts.GOTHIC_FONT;
        this.textBar = String.valueOf(this.currentResource);
    }

    /*public static void setAllFixedWidth(BitmapFont font){
        BitmapFont.BitmapFontData data = font.getData();
        int maxAdvance = 0;
        for (int index = 0, end = 65536; index < end; index++){
            BitmapFont.Glyph g = data.getGlyph((char) index);
            if (g != null && g.xadvance > maxAdvance) maxAdvance = g.xadvance;
        }
        for (int index = 0, end = 65536; index < end; index++){
            BitmapFont.Glyph g = data.getGlyph((char) index);
            if (g == null) continue;
            g.xoffset += (maxAdvance - g.xadvance) / 2;
            g.xadvance = maxAdvance;
            g.kerning = null;
            g.fixedWidth = true;
        }
    }*/

    public void setCurrentResource(int currentResource){
        this.currentResource = currentResource;
    }

    public String getTextBar() {
        return textBar;
    }

    public Vector2 getPosition() {
        return position;
    }
}
