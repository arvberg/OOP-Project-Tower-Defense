package model;

import java.awt.*;

public abstract class Enemy extends Unit implements EnemySpeed{
    public Enemy(Point position, Dimension size) {
        super(position, size);
    }
}
