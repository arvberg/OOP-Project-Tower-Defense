package com.IONA.TowerDefense.model.map;

import java.util.List;

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
