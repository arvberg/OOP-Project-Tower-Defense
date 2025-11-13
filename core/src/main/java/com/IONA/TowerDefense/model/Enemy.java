package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.view.GameFrame;

import javax.swing.*;
import java.awt.*;

import static com.IONA.TowerDefense.model.Direction.SOUTH;
import static javax.swing.SwingConstants.*;

public abstract class Enemy{

    protected int dir = 0;
    protected int hp;
    protected int speed;
    protected int gold;
    protected Point coor;
    protected Rectangle hitBox;
    protected ImageIcon enemyImage;
    protected boolean completedPath;
    protected Direction dir;
    protected int segmentIndex = 0;
    GameModel model;

    public Enemy(int difficulty, GameModel model) {
        coor = new Point(GameFrame.BORDERSIZE, 7*GameFrame.TILESIZE + GameFrame.BORDERSIZE);
        setHitBox();
        this.model = model;
    }

    public Rectangle  getHitBox() {
        return hitBox;
    }

    public void move() {
        switch(dir) {
            case NORTH:
                coor.y -= speed;
                break;
            case EAST:
                coor.x += speed;
                break;
            case SOUTH:
                coor.y += speed;
                break;
            case WEST:
                coor.x -=  speed;
        }
        setHitBox();
    }

    public boolean outsideSegment(Point enemyPosition, Point segmentEnd, Direction direction) {

        return switch (direction) {
            case NORTH -> enemyPosition.getY() < segmentEnd.getY();
            case EAST -> enemyPosition.getX() > segmentEnd.getX();
            case SOUTH -> enemyPosition.getY() > segmentEnd.getY();
            case WEST -> enemyPosition.getX() < segmentEnd.getX();
            default -> false;
        };
    }

    public void reduceHP(int dmg) {
        hp -= dmg;
    }

    public boolean isDead() {
        if(hp < 0 ) return true;
        return false;
    }

    public void setHitBox() {
        hitBox = new Rectangle(coor.x + 9, coor.y + 9, 30, 30);
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGold() {
        return gold;
    }

    public Point getCoor() {
        return coor;
    }

    public void setCoor(Point coor) {
        this.coor = coor;
    }

    public int getSegmentIndex() {
        return segmentIndex;
    }

    public void setSegmentIndex(int segmentIndex) {
        this.segmentIndex = segmentIndex;
    }

    public void setToNewSegment(Point newCoor, Direction newDir, int newSegmentIndex) {
        this.coor = newCoor;
        this.dir = newDir;
        this.segmentIndex = newSegmentIndex;
    }

    public ImageIcon getImage() {
        return enemyImage;
    }
}
