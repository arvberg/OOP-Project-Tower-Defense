package com.IONA.TowerDefense.model;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Path {

    private List<Segment> segments;

    public Path(List<Segment> segments) {
        this.segments = segments;
    }

    public Segment getSegment(int index) {
        return segments.get(index);
    }

    public int getSegmentsLength(List<Segment> segments) {
        int segmentsLength = 0;
        for (Segment segment : segments) {
            segmentsLength += segment.getLength();
        } return segmentsLength;
    }

    public Segment getNextSegment(int index) {
        return segments.get(index + 1);
    }

    public Vector2 getStart() {
        return segments.get(0).getStartPosition();
    }

    public int segmentCount() {
        return segments.size();
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
