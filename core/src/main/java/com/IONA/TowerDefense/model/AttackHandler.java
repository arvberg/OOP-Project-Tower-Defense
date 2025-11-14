package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.projectiles.Projectile;

import java.awt.*;
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
        this.enemies = model.getEnemies();
        this.projectiles = model.getProjectiles();
        this.towers = model.getTowers();
    }

    public void update() {
        //detection check
    }

    public void attack() {

    }

    public boolean withinRadius(Enemy enemy, Tower tower) {
        return false; //placeholder
    }

    public Enemy getTarget(Projectile projectile) {
        return null;
    }

    public void fireProjectile(Tower tower, Enemy enemy) {
        int damage = 5;
        int speed = 5;

        double towerX = tower.getPosition().getX();
        double towerY = tower.getPosition().getY();
        double enemyX = enemy.getCoor().getX();
        double enemyY = enemy.getCoor().getY();

        double vx = enemyX - towerX;
        double vy = enemyY - towerY;

        double length =  Math.sqrt(vx * vx + vy * vy);

        double dirX =  vx / length;
        double dirY =  vy / length;

        projectiles.add(new Projectile(damage, speed, towerX, towerY, dirX, dirY));
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public void projectileHit(Projectile projectile, Enemy enemy) {
        Rectangle hitbox = enemy.getHitBox();
        Boolean hit = hitbox.contains(projectile.getX(), projectile.getY());
        int damage = projectile.getDamage();
        if (hit) {
            projectiles.remove(projectile);
            enemy.takeDamage(damage);
        }
        if (enemy.isDead()) {
            enemies.remove(enemy);
        }
    }

    public void followEnemy(Projectile projectile, Enemy enemy) {
        if (enemy != null && !enemy.isDead()) {
            setProjectileDir(projectile, enemy);
        }
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
