package com.IONA.TowerDefense.model.map;

import java.util.List;

/**
 * Represents a path in the game along which enemies move.
 * <p>
 * A path is composed of multiple {@link Segment} objects defining
 * the path's route from start to end.
 */
public class Path {

    private final List<Segment> segments;

    public Path(List<Segment> segments) {
        this.segments = segments;
    }

    public Segment getSegment(int index) {
        return segments.get(index);
    }

    public int segmentCount() {
        return segments.size();
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
