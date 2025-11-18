package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.List;

public class PathFactory {

    public static Path examplePath1() {
        Path path = new Path(List.of(
            // Segment 0: från (0,2) → (4,2) (EAST)
            new Segment(new Point(2, 5), 2, Direction.SOUTH),

            // Segment 1: från (4,2) → (4,4) (NORTH)
            new Segment(new Point(2, 3), 3, Direction.EAST),

            // Segment 2: från (4,4) → (8,4) (EAST)
            new Segment(new Point(5, 3), 6, Direction.SOUTH)
        ));
        return path;
    }
}
