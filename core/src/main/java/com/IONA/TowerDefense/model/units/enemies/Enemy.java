package com.IONA.TowerDefense.model.units.enemies;

//import com.IONA.TowerDefense.view.GameFrame;
import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.GameModel;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;


import javax.swing.*;
import java.awt.*;

import static com.IONA.TowerDefense.model.Direction.SOUTH;
import static javax.swing.SwingConstants.*;

public abstract class Enemy{

    protected Direction dir;
    protected int hp;
    protected float speed;
    protected int gold;
    protected Vector2 coor;
    protected Rectangle hitBox;
    protected ImageIcon enemyImage;
    protected boolean completedPath; // maybe redundant
    protected int segmentIndex = 0;
    GameModel model;
    public Texture texture;

    public Enemy(int difficulty, GameModel model) {

        this.model = model;
        this.texture = new Texture("ProtTower.png");

    }

    public Rectangle  getHitBox() {
        return hitBox;
    }

    public void move() {

        if (coor == null) {
            coor = new Vector2(0, 2);
        }
        float delta = Gdx.graphics.getDeltaTime();  // sekunder per frame
        float step = speed * delta;                 // speed = units per sekund
        switch(dir) {
            case NORTH:
                coor.y += speed;
                break;
            case EAST:
                coor.x += speed;
                break;
            case SOUTH:
                coor.y -= speed;
                break;
            case WEST:
                coor.x -= speed;
        }
        setHitBox();
    }

    public boolean outsideSegment(Point enemyPosition, Point segmentEnd, Direction direction) {

        return switch (direction) {
            case NORTH -> enemyPosition.getY() <= segmentEnd.getY();
            case EAST -> enemyPosition.getX() >= segmentEnd.getX();
            case SOUTH -> enemyPosition.getY() >= segmentEnd.getY();
            case WEST -> enemyPosition.getX() <= segmentEnd.getX();
            default -> false;
        };
    }

    public void reduceHP(int dmg) {
        hp -= dmg;
    }

    public boolean isDead() {
        if(hp <= 0 ) return true;
        return false;
    }

    public void setHitBox() {
        if (hitBox == null) {
            hitBox = new Rectangle(coor.x, coor.y, 0.5f, 0.5f);
        } else {
            hitBox.set(coor.x, coor.y, 0.5f, 0.5f);
        }
    }


    public Direction getDir() {
        return dir;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public int getHp() {
        return hp;
    }

    public float getSpeed() {
        return speed;
    }

    public int getGold() {
        return gold;
    }

    public Vector2 getCoor() {
        return coor;
    }

    public void setCoor(Vector2 coor) {
        this.coor = coor;
    }

    public int getSegmentIndex() {
        return segmentIndex;
    }

    public void setSegmentIndex(int segmentIndex) {
        this.segmentIndex = segmentIndex;
    }

    public void setToNewSegment(Point newCoor, Direction newDir, int newSegmentIndex) {
        if (this.coor == null) {
            this.coor = new Vector2();
        }
        this.coor.set(newCoor.x, newCoor.y);
        this.dir = newDir;
        this.segmentIndex = newSegmentIndex;
    }

    public ImageIcon getImage() {
        return enemyImage;
    }
}
