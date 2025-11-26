package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.Resource;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class ResourceDrawer {
    public static void drawResources(List<Resource> resources, SpriteBatch batch) {
        for (Resource resource : resources) {
            if (resource.font != null){
                Vector2 position = resource.getPosition();
                batch.setColor(resource.color);
                resource.font.draw(batch, resource.getTextBar(), position.x, position.y);
                batch.setColor(Color.WHITE);
            }
        }
    }
}
