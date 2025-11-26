package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.units.Unit;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

import javax.swing.*;

import static com.IONA.TowerDefense.HeartBeat.delta;
import static java.lang.Math.abs;

public abstract class Enemy extends Unit {

    protected Direction dir;
    protected int hp;
    protected float speed;
    protected int gold;
    protected Rectangle hitBox;
    protected float width;
    protected float height;
    protected int segmentIndex = 0;
    protected int damage;
    public Texture texture;

    public Enemy(int difficulty) {
        this.texture = new Texture("Enemy_temp_02.png");
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void move() {

        if (position == null) {
            position = new Vector2(0, 2);
        }
         // sekunder per frame
    // speed = units per sekund
        switch (dir) {
            case NORTH -> position.y += speed*delta;
            case SOUTH -> position.y -= speed*delta;
            case EAST -> position.x += speed*delta;
            case WEST -> position.x -= speed*delta;
        }
        setHitBox(width, height);
    }

    public boolean outsideSegment(Vector2 enemyPosition, Vector2 segmentEnd, Direction direction) {

        return switch (direction) {
            case NORTH -> enemyPosition.y >= segmentEnd.y;
            case SOUTH -> enemyPosition.y <= segmentEnd.y;
            case EAST -> enemyPosition.x >= segmentEnd.x;
            case WEST -> enemyPosition.x <= segmentEnd.x;
        };
    }

    public void setHitBox(float width, float height) {


        float newX = (position.x - width/2);
        float newY = (position.y - height/2);


        if (hitBox == null) {
            hitBox = new Rectangle(newX, newY, width, height);
        } else {
            hitBox.set(newX, newY, width, height);
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public int getHp() {
        return hp;
    }

    public int getSegmentIndex() {
        return segmentIndex;
    }

    public void setToNewSegment(Vector2 newPosition, Direction newDir, int newSegmentIndex) {
        if (this.position == null) {
            this.position = new Vector2();
        }
        this.position.set(newPosition.x, newPosition.y);
        this.dir = newDir;
        this.segmentIndex = newSegmentIndex;
    }

    public int getDamageNumber(){
        return -abs(damage);
    }

}
