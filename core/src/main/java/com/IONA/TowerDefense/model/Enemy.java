package com.IONA.TowerDefense.model;

import javax.swing.*;
import java.awt.*;

public abstract class Enemy extends Unit{
    public Enemy(Point position, Dimension size) {
        super(position, size);
    }
    protected int dir = 0;
    protected int hp;
    protected int speed;
    protected int gold;
    protected Point coor;
    protected Rectangle hitBox;

    public Enemy() {
        super();
    }

    /* public Enemy(){
    coord
    hitbox

    public Rectangle getHitBox(){
    return hitBox;
    }
    }*/

    public void move(){
        switch(dir){
            case 0:
                coor.y -= 2;
                break;
            case 1:
                coor.y += 2;
                break;
            case 2:
                coor.x -= 2;
                break;
            case 3:
                coor.x += 2;
        }
        //setHitBox();
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
}
