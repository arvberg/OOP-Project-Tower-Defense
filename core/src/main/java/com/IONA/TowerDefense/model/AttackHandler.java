package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.projectiles.Projectile;

import java.util.List;

public class AttackHandler {
    private int width;
    private int height;
    private GameModel model;
    private List<Enemy> enemies = model.getEnemies();
    private List<Projectile> projectiles = model.getProjectiles();
    private List<Tower> towers = model.getTowers();

    public void attackHandler(int width, int height, GameModel model) {
        this.width = width;
        this.height = height;
        this.model = model;
    }

    public void update()

    public boolean withinRadius(Enemy enemy, Tower tower) {
        return false; //placeholder
    }

    public void setProjectileDir(Projectile projectile, Enemy target) {
        double posX = projectile.getY();
        double posY = projectile.getY();

        double targetX = target.getCoor().getX();
        double targetY = target.getCoor().getY();

        double dx = posX - targetX;
        double dy = posY - targetY;

        double length =  Math.sqrt(dx * dx + dy * dy);
        double dirX = dx / length;
        double dirY = dy / length;

        projectile.setDir(dirX, dirY);
    }
}
