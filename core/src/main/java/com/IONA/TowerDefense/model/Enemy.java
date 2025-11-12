package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.view.GameFrame;

import java.awt.*;

import static com.IONA.TowerDefense.model.Direction.*;

public abstract class Enemy extends Unit{
    public Enemy(Point position, Dimension size) {
        super(position, size);
    }
    protected int hp;
    protected int speed;
    protected int gold;
    protected Point coor;
    protected Rectangle hitBox;
    protected boolean completedPath;
    protected Direction dir = SOUTH;
    protected int segmentIndex = 0;

    public Enemy() {
        coor = new Point(GameFrame.BORDERSIZE, 7*GameFrame.TILESIZE + GameFrame.BORDERSIZE);
        setHitBox();
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

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {

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

    public boolean outsideSegment(Point enemyPosition, Point segmentEnd, Direction direction) {

        return switch (direction) {
            case NORTH -> enemyPosition.getY() < segmentEnd.getY();
            case EAST -> enemyPosition.getX() > segmentEnd.getX();
            case SOUTH -> enemyPosition.getY() > segmentEnd.getY();
            case WEST -> enemyPosition.getX() < segmentEnd.getX();
            default -> false;
        };
    }
}
