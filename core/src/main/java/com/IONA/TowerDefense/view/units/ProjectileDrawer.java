package com.IONA.TowerDefense.view.units;

import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class ProjectileDrawer {

    public static void drawProjectiles(List<Projectile> projectiles, SpriteBatch batch){
        for (Projectile p : projectiles) {
            Vector2 proj = p.getPosition();
            batch.draw(p.projectileIcon, proj.x, proj.y, 0.18f, 0.18f);
        }
    }

}
