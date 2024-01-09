package org.anne.aoc2019;

import org.anne.common.Day;
import org.anne.common.Point3d;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day20 extends Day {

    public static void main(String[] args) {
        Day day = new Day20();
        day.setName("Donut Maze");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }
    
    public static int part1(List<String> input) {
        DonutMaze maze = getPortalCodes(input);
        return path(maze);
    }

    public static int part2(List<String> input) {
        int mazeWidth = input.stream().mapToInt(String::length).max().orElse(0);
        DonutMaze maze = getPortalCodes(input);
        return path(maze, mazeWidth, input.size());
    }
    
    static DonutMaze getPortalCodes(List<String> input) {
        Point start = null, end = null;
        HashMap<Point, Character> portalPoints = new HashMap<>();
        Set<Point> pathway = new HashSet<>();
        Map<Point, Point> portals = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                Point point = new Point(x, y);
                char c = line.charAt(x);
                switch (c) {
                    case '.' -> pathway.add(point);
                    case ' ', '#' -> {}
                    default -> portalPoints.put(point, c);
                }
            }
        }
        Map<Point, String> portalCodes = new HashMap<>();
        for (var p1 : portalPoints.keySet()) {
            char c1 = portalPoints.get(p1);
            for (var neighbor : List.of(new Point(p1.x + 1, p1.y), new Point(p1.x, p1.y + 1))) {
                if (portalPoints.containsKey(neighbor)) {
                    var c2 = portalPoints.get(neighbor);
                    Point adjacent = getAdjacentPathPoint(pathway, p1, neighbor);
                    String code;
                    if (c1 == 'A' && c2 == 'A') {
                        start = adjacent;
                        continue;
                    } else if (c1 == 'Z' && c2 == 'Z') {
                        end = adjacent;
                        continue;
                    } else {
                        code = "" + c1 + c2;
                    }
                    if (portalCodes.entrySet().stream().noneMatch(e -> e.getValue().equals(code))) {
                        portalCodes.put(adjacent, code);
                    } else {
                        var adjacent2 = portalCodes.entrySet().stream().filter(e -> e.getValue().equals(code)).findFirst().orElseThrow().getKey();
                        if (!adjacent2.equals(adjacent)) {
                            portals.put(adjacent, adjacent2);
                            portals.put(adjacent2, adjacent);
                        }
                    }
                }
            }
        }
        return new DonutMaze(pathway, portals, start, end);
    }

    private static Point getAdjacentPathPoint(Set<Point> pathway, Point p1, Point p2) {
        List<Point> neighbors = new ArrayList<>();
        neighbors.addAll(neighbors(p1));
        neighbors.addAll(neighbors(p2));
        while (true) {
            for (var neighbor : neighbors) {
                if (pathway.contains(neighbor)) {
                    return neighbor;
                }
            }
        }
    }

    public static int path(DonutMaze maze) {
        Map<Point, Integer> currentCost = new HashMap<>();
        currentCost.put(maze.start, 0);
        Queue<Point> queue = new LinkedList<>();
        queue.add(maze.start);
        while(!queue.isEmpty()) {
            Point current = queue.poll();
            if(current.equals(maze.end)) {
                return currentCost.get(current);
            }
            if(maze.portals.containsKey(current)) {
                Point portal = maze.portals.get(current);
                int cost = currentCost.get(current) + 1;
                if(cost < currentCost.getOrDefault(portal, Integer.MAX_VALUE)) {
                    currentCost.put(portal, cost);
                    queue.add(portal);
                }
            }
            for(Point point : neighbors(current)) {
                if(maze.pathway.contains(point)) {
                    int cost = currentCost.get(current) + 1;
                    if(cost < currentCost.getOrDefault(point, Integer.MAX_VALUE)) {
                        currentCost.put(point, cost);
                        queue.add(point);
                    }
                }
            }
        }
        return -1;
    }

    static int path(DonutMaze maze, int width, int height) {
        Map<Point3d, Integer> currentCost = new HashMap<>();
        Point3d current3d = new Point3d(maze.start, 0);
        currentCost.put(current3d, 0);
        Queue<Point3d> queue = new LinkedList<>();
        queue.add(current3d);
        while(!queue.isEmpty()) {
            current3d = queue.poll();
            if(current3d.equals(new Point3d(maze.end, 0))) {
                return currentCost.get(current3d);
            }
            Point current = new Point(current3d.x(), current3d.y());
            if(maze.portals.containsKey(current)) {
                Point portal = maze.portals.get(current);
                Point3d point3d = null;
                if(current.x <= 2 || current.x >= width - 3 || current.y <= 2 || current.y >= height - 3) {
                    if(current3d.z() > 0) {
                        point3d = new Point3d(portal,current3d.z() - 1);
                    }
                } else {
                    if(current3d.z() < maze.portals.size() / 2) {
                        point3d = new Point3d(portal,current3d.z() + 1);
                    }
                }
                if(point3d != null) {
                    int cost = currentCost.get(current3d) + 1;
                    if(cost < currentCost.getOrDefault(point3d, Integer.MAX_VALUE)) {
                        currentCost.put(point3d, cost);
                        queue.add(point3d);
                    }
                }
            }
            for(Point point : neighbors(current)) {
                if(maze.pathway.contains(point)) {
                    Point3d destination = new Point3d(point, current3d.z());
                    int cost = currentCost.get(current3d) + 1;
                    if(cost < currentCost.getOrDefault(destination, Integer.MAX_VALUE)) {
                        currentCost.put(destination, cost);
                        queue.add(destination);
                    }
                }
            }
        }
        return -1;
    }

    record DonutMaze (Set<Point> pathway, Map<Point, Point> portals, Point start, Point end) {}

    private static List<Point> neighbors(Point point) {
        return Arrays.asList(
                new Point(point.x, point.y - 1),
                new Point(point.x, point.y + 1),
                new Point(point.x - 1, point.y),
                new Point(point.x + 1, point.y));
    }
}
