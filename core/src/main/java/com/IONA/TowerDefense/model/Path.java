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

    /*

    public Point endPointCoordinate(List<Segment> waypoints) {
        int sumX = 0;
        int sumY = 0;

        for (int w = 0; w < waypoints.size() - 1; w++) {
            Segment waypoint = waypoints.get(w);
            int distanceX = waypoints.get(w + 1).getX() - waypoints.get(w).getX();
            int distanceY = waypoints.get(w + 1).getY() - waypoints.get(w).getY();

            switch (waypoint.getDirection()) {
                case 0: // upp
                    sumY += distanceY;
                    break;
                case 1: // höger
                    sumX += distanceX;
                    break;
                case 2: // ner
                    sumY += distanceY;
                    break;
                case 3: // vänster
                    sumX += distanceX;
                    break;
                default:
                    // om riktningen är ogiltig
                    break;
            }
        }
        return new Point(sumX, sumY);
    }

    public boolean isCompletePath(List<Segment> waypoints) {
        int size = waypoints.size();
        Segment endWaypoint = waypoints.get(size -1);
        int endWayPointX = endWaypoint.getX();
        int endWayPointY = endWaypoint.getY();

        Point endPointReference = endPointCoordinate(waypoints);
        int referenceX = endPointReference.x;
        int referenceY = endPointReference.y;

        return endWayPointX == referenceX && endWayPointY == referenceY;
    }

     */
}
