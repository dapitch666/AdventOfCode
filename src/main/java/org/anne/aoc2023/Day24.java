package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.List;

public class Day24 extends Day{
    public static void main(String[] args) {
        Day day = new Day24();
        List<String> input = day.readFile();
        day.setPart1(part1(input, 200000000000000L, 400000000000000L));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static int part1(List<String> input, long min, long max) {
        var hails = input.stream().map(s -> {
            var split = s.replaceAll("\\s", "").split("@");
            var position = split[0].split(",");
            var velocity = split[1].split(",");
            return new Hail(Long.parseLong(position[0]), Long.parseLong(position[1]), Long.parseLong(position[2]),
                    Long.parseLong(velocity[0]), Long.parseLong(velocity[1]), Long.parseLong(velocity[2]));
        }).toList();
        int intersections = 0;
        for (int i = 0; i < hails.size(); i++) {
            for (int j = i + 1; j < hails.size(); j++) {
                if (i == j) continue;
                intersections += hails.get(i).intersects2d(hails.get(j), min, max) ? 1 : 0;
            }
        }
        return intersections;
    }

    public static int part2(List<String> input) {
        return 0;
    }
    
    record Hail(long positionX, long positionY, long positionZ, long velocityX, long velocityY, long velocityZ) {
        float slope() {
            return (float) velocityY / velocityX;
        }
        boolean intersects2d(Hail other, long min, long max) {
            var slope = slope();
            var otherSlope = other.slope();
            if (slope == otherSlope) {
                return false;
            }
            var intersectX = ((otherSlope * other.positionX) - (slope * positionX) + positionY - other.positionY) / (otherSlope - slope);
            if (intersectX < min || intersectX > max) {
                return false;
            }
            var intersectY = (slope * (intersectX - positionX)) + positionY;
            if (intersectY < min || intersectY > max) {
                return false;
            }
            return isFuture(intersectX, intersectY) && other.isFuture(intersectX, intersectY);
        }

        private boolean isFuture(float x, float y) {
            return (velocityX >= 0 || positionX >= x) && (velocityX <= 0 || positionX <= x) &&
                    (velocityY >= 0 || positionY >= y) && (velocityY <= 0 || positionY <= y);
        }
    }
}
