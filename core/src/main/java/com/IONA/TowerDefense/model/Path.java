package com.IONA.TowerDefense.model;

import java.awt.*;
import java.util.List;

public class Path {
    private List<WayPoint> waypoints;

    public Path(List<WayPoint> waypoints) {
        this.waypoints = waypoints;
    }

    public WayPoint getNextWaypoint() {}

    public void addWaypoint(double x, double y, double direction) {
        waypoints.add(new WayPoint(x, y, direction));
    }

    public List<WayPoint> getWaypoints() {
        return waypoints;
    }

    public Point endPointCoordinate(List<WayPoint> waypoints) {
        double sumX = 0;
        double sumY = 0;
        for (int w = 0; w < waypoints.size() -1; w++) {
            double distanceX = waypoints.get(w).getX() - waypoints.get(w + 1).getX();
            double distanceY = waypoints.get(w).getY() - waypoints.get(w + 1).getY();
            switch (waypoints.getDirection()) {
                case 0:
                    sumY =+ distanceY;
                    break;

                    case 1:
                        sumX =+ distanceX;
                        break;

                        case 2:
                            sumY =+ distanceY;
                            break;

                            case 3:
                                sumY =+ distanceY;
            }
        }
    }

    public boolean isComplete() {

    }
}
