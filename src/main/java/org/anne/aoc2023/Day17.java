package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Day17 extends Day {
    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public void execute() {
        setName("Clumsy Crucible");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }


    public static int part1(List<String> input) {
        var map = GridHelper.getIntGrid(input);
        return getBestPathHeat(map, 0, 3);
    }

    public static int part2(List<String> input) {
        var map = GridHelper.getIntGrid(input);
        return getBestPathHeat(map, 4, 10);
    }

    private static int getBestPathHeat(int[][] map, int minimumDirectionCount, int maximumDirectionCount) {
        var seen = new HashSet<>();
        var pq = new PriorityQueue<Status>();
        var end = new Point(map[0].length - 1, map.length - 1);
        pq.add(new Status(new Point(1, 0), Direction.EAST, 1, map[0][1]));
        pq.add(new Status(new Point(0, 1), Direction.SOUTH, 1, map[1][0]));
        while (!pq.isEmpty()) {
            var status = pq.poll();
            if (status.point.equals(end) && status.directionCount >= minimumDirectionCount) {
                return status.heatLoss;
            }
            if (seen.contains(status)) {
                continue;
            }
            seen.add(status);
            if (status.directionCount < maximumDirectionCount) {
                var nextPoint = status.direction.move(status.point);
                if (GridHelper.isValidPoint(nextPoint, map)) {
                    pq.add(new Status(
                            nextPoint,
                            status.direction,
                            status.directionCount + 1,
                            status.heatLoss + map[nextPoint.y][nextPoint.x]
                    ));
                }
            }
            if (status.directionCount >= minimumDirectionCount) {
                for (var direction : Direction.values()) {
                    if (direction != status.direction && direction != status.direction.reverse()) {
                        var nextPoint = direction.move(status.point);
                        if (GridHelper.isValidPoint(nextPoint, map)) {
                            pq.add(new Status(
                                    nextPoint,
                                    direction,
                                    1,
                                    status.heatLoss + map[nextPoint.y][nextPoint.x]
                            ));
                        }
                    }
                }
            }
        }
        return -1;
    }

    record Status(Point point, Direction direction, int directionCount, int heatLoss) implements Comparable<Status> {
        @Override
        public int compareTo(Status o) {
            return Integer.compare(heatLoss, o.heatLoss);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Status status)) return false;
            return point.equals(status.point) && direction == status.direction && directionCount == status.directionCount;
        }
        
        @Override
        public int hashCode() {
            return point.hashCode() + direction.hashCode() + directionCount;
        }
    }
}
