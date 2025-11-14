package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.List;

public class PathFactory {

    public static Path examplePath1() {
        Path path = new Path(List.of(
            // Segment 0: från (0,2) → (4,2) (EAST)
            new Segment(new Point(2, 6), 2, Direction.NORTH),

            // Segment 1: från (4,2) → (4,4) (NORTH)
            new Segment(new Point(2, 4), 3, Direction.EAST),

            // Segment 2: från (4,4) → (8,4) (EAST)
            new Segment(new Point(5, 4), 6, Direction.NORTH)
        ));
        return path;
    }
}
