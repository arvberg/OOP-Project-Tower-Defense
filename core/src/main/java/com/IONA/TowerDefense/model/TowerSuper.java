package com.IONA.TowerDefense.model;

import java.awt.*;

public class TowerSuper extends Tower{
    public TowerSuper(Point position, Dimension size) {
        super(position, size);
    }

    @Override
    public Dimension getSize(){
        return new Dimension(200, 200);
    }
}
