package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.List;

public class Path {
    private List<Segment> segments;

    public Path(List<Segment> segments) {
        this.segments = segments;
    }

    public Segment getSegment(int index) {
        return segments.get(index);
    }

    public Segment getNextSegment(int index) {
        return segments.get(index + 1);
    }

    public int segmentCount() {
        return segments.size();
    }

    public List<Segment> getSegments() {
        return getSegments();
    }
}
