package com.IONA.TowerDefense.model.units.enemies;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.Direction;
import com.IONA.TowerDefense.model.units.Unit;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

import javax.swing.*;

public abstract class Enemy extends Unit {

    protected Direction dir;
    protected int hp;
    protected float speed;
    protected int gold;
    protected Rectangle hitBox;
    protected float width;
    protected float height;
    protected int segmentIndex = 0;
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
        float delta = HeartBeat.delta; // sekunder per frame
        float step = speed * delta;    // speed = units per sekund
        switch (dir) {
            case NORTH -> position.y += step;
            case SOUTH -> position.y -= step;
            case EAST -> position.x += step;
            case WEST -> position.x -= step;
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

}
