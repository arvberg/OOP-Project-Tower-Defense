package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;


public class ButtonGroupOneDrawer {

    public static void drawButtons(List<Button> buttons, SpriteBatch batch){
        for (Button b: buttons){
            batch.draw(b.texture, b.getButtonPosition().x, b.getButtonPosition().y, b.width, b.height);
        }

    }

}
