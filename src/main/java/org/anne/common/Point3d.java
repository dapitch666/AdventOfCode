package org.anne.common;

import java.awt.*;

public record Point3d(int x, int y, int z) {

    public Point3d(Point point, int z) {
        this(point.x, point.y, z);
    }
    
    public Point3d add(Point3d other) {
        return new Point3d(x + other.x, y + other.y, z + other.z);
    }
    
    public int absSum() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }
    
    public Point3d reverse() {
        return new Point3d(-x, -y, -z);
    }
    
    public int manhattanDistance(Point3d other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
    }
}
