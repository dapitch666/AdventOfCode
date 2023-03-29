package org.anne.aoc2019;

import org.anne.common.Day;
import org.anne.common.Point3d;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day24 extends Day {

    public static void main(String[] args) {
        Day day = new Day24();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input, 200));
        day.printParts();
    }

    public static long part1(List<String> input) {
        List<Long> states = new ArrayList<>();
        Set<Point> bugs = new HashSet<>();
        int y = 0;
        for (String line : input) {
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') {
                    bugs.add(new Point(x, y));
                }
            }
            y++;
        }
        states.add(getBiodiversity(bugs));
        while (true) {
            Set<Point> newBugs = new HashSet<>();
            Set<Point> finalBugs = bugs;
            for (y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    Point point = new Point(x, y);
                    int neighbours = getNeighbours(point)
                            .stream()
                            .mapToInt(neighbour -> finalBugs.contains(neighbour) ? 1 : 0)
                            .sum();
                    if (bugs.contains(point)) {
                        if (neighbours == 1) {
                            newBugs.add(point);
                        }
                    } else {
                        if (neighbours == 1 || neighbours == 2) {
                            newBugs.add(point);
                        }
                    }
                }
            }
            bugs = newBugs;
            long state = getBiodiversity(bugs);
            if (states.contains(state)) {
                return state;
            } else {
                states.add(state);
            }
        }
    }

    public static long part2(List<String> input, int minutes) {
        Set<Point3d> bugs = new HashSet<>();
        int y = 0;
        for (String line : input) {
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') {
                    bugs.add(new Point3d(x, y, 0));
                }
            }
            y++;
        }
        for (int i = 0; i < minutes; i++) {
            Set<Point3d> newBugs = new HashSet<>();
            Set<Point3d> finalBugs = Set.copyOf(bugs);
            Set<Point3d> queueSet = new HashSet<>(bugs);
            for (Point3d bug : bugs) {
                queueSet.addAll(getNeighbours(bug));
            }
            Queue<Point3d> queue = new LinkedList<>(queueSet);
            while (!queue.isEmpty()) {
                Point3d point = queue.poll();
                List<Point3d> neighbours = getNeighbours(point);
                int infectedNeighbours = neighbours
                        .stream()
                        .mapToInt(neighbour -> finalBugs.contains(neighbour) ? 1 : 0)
                        .sum();
                if ((bugs.contains(point) && infectedNeighbours == 1) ||
                        (!bugs.contains(point) && (infectedNeighbours == 1 || infectedNeighbours == 2))) {
                    newBugs.add(point);
                }
            }
            bugs = newBugs;
        }
        return bugs.size();
    }

    static List<Point3d> getNeighbours(Point3d point) {
        List<Point3d> neighbours = new ArrayList<>();
        for (Point3d n : List.of(
                new Point3d(point.x() - 1, point.y(), point.z()),
                new Point3d(point.x() + 1, point.y(), point.z()),
                new Point3d(point.x(), point.y() - 1, point.z()),
                new Point3d(point.x(), point.y() + 1, point.z())
        )) {
            if (n.x() == 2 & n.y() == 2) {
                if (point.x() == 1) {
                    for (int y = 0; y < 5; y++) {
                        neighbours.add(new Point3d(0, y, point.z() + 1));
                    }
                } else if (point.x() == 3) {
                    for (int y = 0; y < 5; y++) {
                        neighbours.add(new Point3d(4, y, point.z() + 1));
                    }
                } else if (point.y() == 1) {
                    for (int x = 0; x < 5; x++) {
                        neighbours.add(new Point3d(x, 0, point.z() + 1));
                    }
                } else if (point.y() == 3) {
                    for (int x = 0; x < 5; x++) {
                        neighbours.add(new Point3d(x, 4, point.z() + 1));
                    }
                }
            } else if (n.x() >= 0 && n.x() < 5 && n.y() >= 0 && n.y() < 5) {
                neighbours.add(n);
            } else {
                if (n.x() == -1) {
                    neighbours.add(new Point3d(1, 2, point.z() - 1));
                } else if (n.x() == 5) {
                    neighbours.add(new Point3d(3, 2, point.z() - 1));
                } else if (n.y() == -1) {
                    neighbours.add(new Point3d(2, 1, point.z() - 1));
                } else if (n.y() == 5) {
                    neighbours.add(new Point3d(2, 3, point.z() - 1));
                }
            }
        }
        return neighbours;
    }

    private static List<Point> getNeighbours(Point point) {
        return List.of(
                new Point(point.x - 1, point.y),
                new Point(point.x + 1, point.y),
                new Point(point.x, point.y - 1),
                new Point(point.x, point.y + 1)
        );
    }
    
    static long getBiodiversity(Set<Point> bugs) {
        return bugs.stream().mapToLong(bug -> (long) Math.pow(2, bug.x + bug.y * 5)).sum();
    }
}
