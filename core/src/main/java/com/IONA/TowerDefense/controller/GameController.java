package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.Enemy;
import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.Projectile;
import com.IONA.TowerDefense.model.Tower;
import com.IONA.TowerDefense.view.GameView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

<<<<<<< HEAD
import java.util.Vector;
=======
import java.util.List;
>>>>>>> 376c4326fd935aba2eda774295767edc51eb9c29


public class GameController {

    private final GameModel model;
    private final GameView view;
    private final Vector2 mousePos = new Vector2();

    private static final Vector<Tower> towers = new Vector<>();
    private static final Vector<Projectile> projectiles = new Vector<>();
    private static final Vector<Enemy> enemies = new  Vector<>();

    public GameController (GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void update() {
        handleMouseInput();
    }

    // handle all input
    private void handleMouseInput() {
    }

    public static void addTower(Tower temp){
        towers.add(temp);
    }

    public static void addEnemy(Enemy temp){
        enemies.add(temp);
    }

    public static void addProjectile(Projectile temp) {
        projectiles.add(temp);
    }

    public static void removeEnemy(Enemy temp){
        enemies.remove(temp);
    }

    public static Vector<Tower> getTowers(){
        return towers;
    }

    public static Vector<Enemy> getEnemies(){
        return enemies;
    }

    public static Vector<Projectile> getProjectiles(){
        return projectiles;
    }

}
