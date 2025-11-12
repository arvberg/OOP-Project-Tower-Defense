package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.Enemy;
import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.Projectile;
import com.IONA.TowerDefense.model.Tower;
import com.IONA.TowerDefense.view.Draw;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;


public class GameController {

    private final GameModel model;
    private final Draw view;
    private final Vector2 mousePos = new Vector2();


    private static final Vector<Tower> towers = new Vector<>();
    private static final Vector<Projectile> projectiles = new Vector<>();
    private static final Vector<Enemy> enemies = new  Vector<>();

    public GameController (GameModel model, Draw view) {
        this.model = model;
        this.view = view;
    }

    public void update() {
        handleMouseInput();
    }

    // handle all input
    private void handleMouseInput() {

        mousePos.set(Gdx.input.getX(), Gdx.input.getY());

        // Left-click: Select a tower
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            model.onLeftClick(mousePos.x, mousePos.y);
        }

        // Right-click: do something else
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            model.onRightClick(mousePos.x, mousePos.y);
        }

        // Optional: drag or move a selected object while holding left button
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            model.onMouseDrag(mousePos.x, mousePos.y);
        }
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
