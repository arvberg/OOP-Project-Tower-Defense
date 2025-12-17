package com.IONA.TowerDefense.model.units.towers.attackStrategies;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.projectiles.Projectile;
import com.IONA.TowerDefense.model.units.projectiles.ProjectileBasic;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Vector;

public class ProjectileAttackStrategy implements AttackStrategy {

    @Override
    public void attack(Tower tower, List<Enemy> targets, List<Projectile> projectiles) {
        for (Enemy e : targets) {

            Vector2 pDirection = VectorUtils.direction(tower.getPosition(), e.getPosition());

            float pSpeed = tower.getProjectileSpeed();
            int pDamage = tower.getDamage();
            Vector2 towerPos = new Vector2(tower.getPosition());
            Projectile p = new ProjectileBasic(pDamage, pSpeed, towerPos, pDirection);
            projectiles.add(p);
        }
    }
}
