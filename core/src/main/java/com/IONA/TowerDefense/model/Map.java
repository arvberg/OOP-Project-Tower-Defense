package com.IONA.TowerDefense.model;

public class Map {

    public final int height;
    public final int width;
    public final Path path;

    public Map(int height, int width, Path path) {
        this.height = height;
        this.width = width;
        this.path = path;
    }

}
