package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.anne.common.Direction.*;

public class Day10 extends Day {
    public static void main(String[] args) {
        Day day = new Day10();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Pipe Maze");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }
    
    public static int part1(List<String> input) {
        var grid = getGrid(input);
        var start = getStart(grid);
        var direction = NORTH;
        
        var nextDirection = getNext(grid, start, direction);
        var nextPoint = Direction.getPoint(nextDirection, start);
        int steps = 1;
        while (grid[nextPoint.y][nextPoint.x] != 'S') {
            direction = nextDirection;
            nextDirection = getNext(grid, nextPoint, direction);
            nextPoint = Direction.getPoint(nextDirection, nextPoint);
            steps++;
        }
        return steps / 2;
    }

    

    public static int part2(List<String> input) {
        var grid = getGrid(input);
        int[][] doubledGrid = new int[grid.length * 2 + 1][grid[0].length * 2 + 1];
        var start = getStart(grid);
        var direction = NORTH;

        doubledGrid[start.y * 2 + 1][start.x * 2 + 1] = 1;
        var nextDirection = getNext(grid, start, direction);
        var nextPoint = Direction.getPoint(nextDirection, start);

        while (grid[nextPoint.y][nextPoint.x] != 'S') {
            int doubledX = nextPoint.x * 2 + 1;
            int doubledY = nextPoint.y * 2 + 1;
            char c = grid[nextPoint.y][nextPoint.x];
            if (c != '.') {
                doubledGrid[doubledY][doubledX] = 1;
            }
            switch (c) {
                case '|' -> {
                    doubledGrid[doubledY - 1][doubledX] = 1;
                    doubledGrid[doubledY + 1][doubledX] = 1;
                }
                case '-' -> {
                    doubledGrid[doubledY][doubledX - 1] = 1;
                    doubledGrid[doubledY][doubledX + 1] = 1;
                }
                case 'L' -> {
                    doubledGrid[doubledY - 1][doubledX] = 1;
                    doubledGrid[doubledY][doubledX + 1] = 1;
                }
                case 'J' -> {
                    doubledGrid[doubledY - 1][doubledX] = 1;
                    doubledGrid[doubledY][doubledX - 1] = 1;
                }
                case '7' -> {
                    doubledGrid[doubledY + 1][doubledX] = 1;
                    doubledGrid[doubledY][doubledX - 1] = 1;
                }
                case 'F' -> {
                    doubledGrid[doubledY + 1][doubledX] = 1;
                    doubledGrid[doubledY][doubledX + 1] = 1;
                }
            }
            direction = nextDirection;
            nextDirection = getNext(grid, nextPoint, direction);
            nextPoint = Direction.getPoint(nextDirection, nextPoint);
        }
        var filled = fill(doubledGrid);
        int area = 0;
        for (int y = 0; y < filled.length; y++) {
            for (int x = 0; x < filled[0].length; x++) {
                if (x % 2 == 1 && y % 2 == 1 && filled[y][x] == 0) {
                    area++;
                }
            }
        }
        return area;
    }

    static List<Point> getNeighbours(Point p, int[][] grid) {
        var neighbours = new ArrayList<Point>();
        if (p.x > 0) {
            neighbours.add(new Point(p.x - 1, p.y));
        }
        if (p.x < grid[0].length - 1) {
            neighbours.add(new Point(p.x + 1, p.y));
        }
        if (p.y > 0) {
            neighbours.add(new Point(p.x, p.y - 1));
        }
        if (p.y < grid.length - 1) {
            neighbours.add(new Point(p.x, p.y + 1));
        }
        return neighbours;
    }

    static int[][] fill(int[][] grid) {
        var filled = new int[grid.length][grid[0].length];
        var toFill = new HashSet<Point>();
        var checked = new HashSet<Point>();
        toFill.add(new Point(0, 0));
        while (!toFill.isEmpty()) {
            var newToFill = new HashSet<Point>();
            for (Point p : toFill) {
                if (grid[p.y][p.x] != 1) {
                    filled[p.y][p.x] = 1;
                    newToFill.addAll(getNeighbours(p, grid).stream().filter(x -> !checked.contains(x)).toList());
                }
                checked.add(p);
            }
            toFill = newToFill;
        }
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 1) {
                    filled[y][x] = 1;
                }
            }
        }
        return filled;
    }
    
    static char[][] getGrid(List<String> input) {
        var height = input.size() + 2;
        var width = input.get(0).length() + 2;
        var grid = new char[height][width];
        for (int y = 0; y < height; y++) {
            if (y == 0 || y == height - 1) {
                for (int x = 0; x < width; x++) {
                    grid[y][x] = '.';
                }
                continue;
            }
            for (int x = 0; x < width; x++) {
                if (x == 0 || x == width - 1) {
                    grid[y][x] = '.';
                    continue;
                }
                char c = input.get(y-1).charAt(x-1);
                grid[y][x] = c;
            }
        }
        return grid;
    }

    private static Point getStart(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'S') {
                    return new Point(x, y);
                }
            }
        }
        return new Point(0, 0); // Should never happen
    }
    
    private static Direction getNext(char[][] grid, Point start, Direction direction) {
        var north = grid[start.y - 1][start.x];
        var south = grid[start.y + 1][start.x];
        var east = grid[start.y][start.x + 1];
        var west = grid[start.y][start.x - 1];
        var current = grid[start.y][start.x]; 
        if (current == 'S') {
            if (north == '|' || north == 'F' || north == '7') {
                return NORTH;
            } else if (south == '|' || south == 'J' || south == 'L') {
                return SOUTH;
            } else if (east == '-' || east == '7' || east == 'J') {
                return EAST;
            } else if (west == '-' || west == 'F' || west == 'L') {
                return WEST;
            }
        } else if (current == '|' || current == '-') {
            return direction;
        } else if (current == 'J') {
            if (direction == SOUTH) {
                return WEST;
            } else if (direction == EAST) {
                return NORTH;
            }
        } else if (current == 'L') {
            if (direction == SOUTH) {
                return EAST;
            } else if (direction == WEST) {
                return NORTH;
            }
        } else if (current == 'F') {
            if (direction == NORTH) {
                return EAST;
            } else if (direction == WEST) {
                return SOUTH;
            }
        } else if (current == '7') {
            if (direction == NORTH) {
                return WEST;
            } else if (direction == EAST) {
                return SOUTH;
            }
        }
        return direction; // Should never happen
    }
}
