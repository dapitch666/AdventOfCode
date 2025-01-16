package org.anne.common;

import java.awt.*;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public Point move(Point start) {
        return switch (this) {
            case NORTH -> new Point(start.x, start.y - 1);
            case SOUTH -> new Point(start.x, start.y + 1);
            case EAST -> new Point(start.x + 1, start.y);
            case WEST -> new Point(start.x - 1, start.y);
        };
    }
    
    public Direction rotate90(boolean clockwise) {
        return switch (this) {
            case NORTH -> clockwise ? EAST : WEST;
            case SOUTH -> clockwise ? WEST : EAST;
            case EAST -> clockwise ? SOUTH : NORTH;
            case WEST -> clockwise ? NORTH : SOUTH;
        };
    }
    
    public Direction reverse() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }

    public static Direction fromChar(char c) {
        return switch (c) {
            case '^' -> NORTH;
            case 'v' -> SOUTH;
            case '<' -> WEST;
            case '>' -> EAST;
            default -> throw new IllegalArgumentException("Invalid direction: " + c);
        };
    }

    public static Direction fromInitial(char c) {
        return switch (c) {
            case 'N' -> NORTH;
            case 'S' -> SOUTH;
            case 'W' -> WEST;
            case 'E' -> EAST;
            default -> throw new IllegalArgumentException("Invalid direction: " + c);
        };
    }

    public char getChar() {
        return switch (this) {
            case NORTH -> '^';
            case SOUTH -> 'v';
            case WEST -> '<';
            case EAST -> '>';
        };
    }
}
