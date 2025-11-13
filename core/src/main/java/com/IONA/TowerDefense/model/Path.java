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

    public int getSegmentsLength(List<Segment> segments) {
        int segmentsLength = 0;
        for (Segment segment : segments) {
            segmentsLength += segment.getLength();
        } return segmentsLength;
    }

    public Segment segmentFromPathLength(int length, Path path) {
        int pathLength = path.getSegmentsLength(path.getSegments());
        int currentSegmentLength = 0;
        int index = 0;
        for (Segment segment : path.getSegments()) {
            currentSegmentLength += segment.getLength();
            index++;
            if (currentSegmentLength > pathLength) {
                return path.getSegment(index);
            }
        }
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
