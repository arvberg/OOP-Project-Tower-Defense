package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.List;

public class Path {
    private List<WayPoint> waypoints;

    public Path(List<WayPoint> waypoints) {
        this.waypoints = waypoints;
    }

    public int wayPointIndex(WayPoint waypoint) {
        return getWaypoints().indexOf(waypoint);
    }

    // TODO create new points based on additional positions and directions
    public void addNewWayPoint(int additionalX, int additionalY, int direction) {
        if (waypoints.isEmpty()) {
            new WayPoint(additionalX, additionalY, direction);
        }
        else {
            WayPoint lastWayPoint = waypoints.get(waypoints.size() - 1);
            int lastWayPointX = lastWayPoint.getX();
            int lastWayPointY = lastWayPoint.getY();

            int newX = lastWayPoint.getX() + additionalX;
            int newY = lastWayPoint.getY() + additionalY;
            WayPoint newWayPoint = new WayPoint(newX, newY, direction);
        }
    }

    public List<WayPoint> getWaypoints() {
        return waypoints;
    }

    public Point endPointCoordinate(List<WayPoint> waypoints) {
        int sumX = 0;
        int sumY = 0;

        for (int w = 0; w < waypoints.size() - 1; w++) {
            WayPoint waypoint = waypoints.get(w);
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

    public boolean isCompletePath(List<WayPoint> waypoints) {
        int size = waypoints.size();
        WayPoint endWaypoint = waypoints.get(size -1);
        int endWayPointX = endWaypoint.getX();
        int endWayPointY = endWaypoint.getY();

        Point endPointReference = endPointCoordinate(waypoints);
        int referenceX = endPointReference.x;
        int referenceY = endPointReference.y;

        return endWayPointX == referenceX && endWayPointY == referenceY;
    }
}
