package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.List;

public class Path {

    private final List<Segment> segments;

    public Path(List<Segment> segments) {
        this.segments = segments;
    }

    public int segmentIndex(Segment segment) {
        return getSegments().indexOf(segment);
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public Path createPath1() {
        Path path = new Path(List.of(
            new Segment(new Point(50, 0),10, Direction.SOUTH),
            new Segment(new Point(50, 10), 10, Direction.EAST),
            new Segment(new Point(60, 10), 60, Direction.SOUTH)
        ));
        return path;
    }
}
