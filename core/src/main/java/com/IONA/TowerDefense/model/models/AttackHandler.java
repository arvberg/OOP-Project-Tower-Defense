package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class AttackHandler {
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private List<Tower> towers;
    private List<Unit> deadUnits;

    public AttackHandler(GameModel model) {
        this.enemies = model.getEnemies();
        this.projectiles = model.getProjectiles();
        this.towers = model.getTowers();
        this.deadUnits = new ArrayList<>();
    }

    public void update() {
        for (Tower tower : towers) {
            tower.update();

                // tower.addTimeSinceLastShot(delta);
                for (Enemy enemy : enemies) {
                    projectileHit2000(enemy);
                    if (withinRadius(enemy, tower) && tower.canShoot()) {

                        fireHoamingProjectile(enemy, tower);
                        tower.resetCooldown();
                        break;

                    }
                }
            }
            for (Projectile projectile : projectiles) {
                updateHoamingProjectile(projectile);
                projectile.move();
            }
        // removeDead();
    }

    public boolean withinRadius(Enemy enemy, Tower tower) {
        float distance = getDistance(enemy, tower);
        return distance < tower.getRange();
    }

    public void fireProjectile(Enemy target, Tower tower) {
        int damage = tower.getDamage();
        float projectileSpeed = tower.getProjectileSpeed();
        float fireRate = tower.getFireRate();
        float towerX = tower.getPosition().x;
        float towerY = tower.getPosition().y;
        float length = getDistance(target, tower);

        // temporary target for demo, change to target later
        float dirX = getDir(enemies.get(0), tower).x;
        float dirY = getDir(enemies.get(0), tower).y;

        //float spawnTime = cumulativeDelay;
        projectiles.add(new Projectile(damage, projectileSpeed, towerX, towerY, dirX, dirY, enemies.get(0)));
    }

    public void removeDead(Unit unit) {
    }

    public void fireHoamingProjectile(Enemy target, Tower tower) {
        int damage = tower.getDamage();
        float projectileSpeed = tower.getProjectileSpeed();
        float towerX = tower.getPosition().x;
        float towerY = tower.getPosition().y;

        Vector2 dir = getDir(tower, target);

        Projectile projectile = new Projectile(damage, projectileSpeed, towerX, towerY, dir.x, dir.y, target);
        projectile.setTarget(target);
        projectiles.add(projectile);
    }

    public void updateHoamingProjectile(Projectile p) {
        Enemy target =  p.getTarget();
        if (target == null) {
            return;
        }
        Vector2 dir = getDir(p, target);
        p.setDir(dir.x, dir.y);
    }

    public void projectileHit2000(Enemy enemy) {
        Rectangle hitbox = enemy.getHitBox();
        for (Projectile projectile : projectiles) {
            boolean hit = hitbox.contains(projectile.getX(), projectile.getY());
            int damage = projectile.getDamage();
            if (hit) {
                projectile.setHit(true);
                enemy.takeDamage(damage);
            }
        }
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
        return to.getX() - from.getX();
    }

    public float getDeltaY(Unit from, Unit to) {
        return to.getY() - from.getY();
    }

    public float getDistance (Unit from, Unit to) {
        float deltaX = getDeltaX(from, to);
        float deltaY = getDeltaY(from, to);

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY); // length
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

}
