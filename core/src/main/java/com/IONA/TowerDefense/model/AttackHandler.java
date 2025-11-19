package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class AttackHandler {
    private int width;
    private int height;
    private GameModel model;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private List<Tower> towers;

    public AttackHandler(int width, int height, GameModel model) {
        this.width = width;
        this.height = height;
        this.model = model;
        this.enemies = model.getEnemies();
        this.projectiles = model.getProjectiles();
        this.towers = model.getTowers();
    }

    public void update() {
        for (Tower tower : towers) {
            // tower.addTimeSinceLastShot(delta);
            for (Enemy enemy : enemies) {
                if (withinRadius(enemy, tower)) {
                    fireProjectile(enemy, tower);
                }
            }
        }
        for (Projectile projectile : projectiles) {
            projectile.move();
        }
    }

    public boolean withinRadius(Enemy enemy, Tower tower) {
        float distance = getDistance(enemy, tower);
        return distance < tower.getRange();
    }

    public void fireProjectile(Enemy target, Tower tower) {
        int damage = tower.getDamage();
        float projectileSpeed = tower.getProjectileSpeed();
        float towerX = tower.getPosition().x;
        float towerY = tower.getPosition().y;
        float length = getLength(target, tower);
        float dirX = getDir(target, tower).x;
        float dirY = getDir(target, tower).y;

        int random = (int) (Math.random() * 200);
        if (random < 5) {
            projectiles.add(new Projectile(damage, projectileSpeed, towerX, towerY, dirX, dirY, target));
        }
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public void projectileHit(Projectile projectile, Enemy enemy) {
        Rectangle hitbox = enemy.getHitBox();
        boolean hit = hitbox.contains(projectile.getX(), projectile.getY());
        int damage = projectile.getDamage();
        if (hit) {
            projectiles.remove(projectile);
            enemy.takeDamage(damage);
        }
    }

    public Vector2 getDir(Unit from, Unit to) {

        float deltaX = getDeltaX(from, to);
        float deltaY = getDeltaY(from, to);
        float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return new Vector2(deltaX/length, deltaY/length);
    }

    public float getLength(Enemy enemy, Tower tower) {

        float towerX = (float) tower.getPosition().x;
        float towerY = (float) tower.getPosition().y;
        float enemyX = enemy.getCoor().x;
        float enemyY = enemy.getCoor().y;

        float vx = enemyX - towerX;
        float vy = enemyY - towerY;

        return (float) Math.sqrt(vx * vx + vy * vy); // length
    }

    public void setProjectileDir(Projectile projectile) {
        float posX = projectile.getY();
        float posY = projectile.getY();

        float dx = posX - 1;
        float dy = posY - 1;

        float length = (float) Math.sqrt(dx * dx + dy * dy);
        float dirX = dx / length;
        float dirY = dy / length;

        projectile.setDir(dirX, dirY);
    }

    public float getDeltaX(Unit from, Unit to) {
        return from.getX() - to.getX();
    }

    public float getDeltaY(Unit from, Unit to) {
        return from.getY() - to.getY();
    }

    public float getDistance (Unit from, Unit to) {
        float deltaX = getDeltaX(from, to);
        float deltaY = getDeltaY(from, to);

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY); // length
    }

}
