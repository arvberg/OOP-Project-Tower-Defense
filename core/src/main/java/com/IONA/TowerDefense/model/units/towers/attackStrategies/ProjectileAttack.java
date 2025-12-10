package com.IONA.TowerDefense.model.units.towers.attackStrategies;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.towers.Tower;

import java.util.List;

public class ProjectileAttack {

    GameModel gameModel;

    public void ProjectileAttack(Tower tower, List<Enemy> targets) {
        Projectile p = new Projectile(tower.getDamage(), tower.getProjectileSpeed(), tower.getPosition(), tower.getPosition());
    }
}
