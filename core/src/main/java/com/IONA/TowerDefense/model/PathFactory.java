package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.List;

public class PathFactory {

    public Path examplePath1() {
        Path path = new Path(List.of(
            new Segment(new Point(50, 0),10, Direction.SOUTH),
            new Segment(new Point(50, 10), 10, Direction.EAST),
            new Segment(new Point(60, 10), 60, Direction.SOUTH)
        ));
        return path;
    }
}
