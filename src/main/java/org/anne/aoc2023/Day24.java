package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.List;

public class Day24 extends Day{
    public static void main(String[] args) {
        new Day24().run();
    }

    @Override
    public void execute() {
        setName("Never Tell Me The Odds");
        List<String> input = readFile();
        setPart1(part1(input, 200000000000000L, 400000000000000L));
        setPart2(part2(input));
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

    public static long part2(List<String> input) {
        var hails = input.stream().map(s -> {
            var split = s.replaceAll("\\s", "").split("@");
            var position = split[0].split(",");
            var velocity = split[1].split(",");
            return new Hail(Long.parseLong(position[0]), Long.parseLong(position[1]), Long.parseLong(position[2]),
                    Long.parseLong(velocity[0]), Long.parseLong(velocity[1]), Long.parseLong(velocity[2]));
        }).toList();
        var matrix = new double[4][4];
        var constants = new double[4];

        for (int i = 0; i < 4; i++) {
            var hail = hails.get(i);
            var other = hails.get(i + 1);
            matrix[i][0] = other.velocityY - hail.velocityY;
            matrix[i][1] = hail.velocityX - other.velocityX;
            matrix[i][2] = hail.positionY - other.positionY;
            matrix[i][3] = other.positionX - hail.positionX;
            constants[i] = hail.positionY() * hail.velocityX() - hail.positionX() * hail.velocityY() +
                    other.positionX() * other.velocityY() - other.positionY() * other.velocityX();
        }

        gauss(matrix, constants);

        long rockX = Math.round(constants[0]);
        long rockY = Math.round(constants[1]);
        long vx = Math.round(constants[2]);

        matrix = new double[2][2];
        constants = new double[2];
        for (int i = 0; i < 2; i++) {
            var hail = hails.get(i);
            var other = hails.get(i + 1);
            matrix[i][0] = hail.velocityX - other.velocityX;
            matrix[i][1] = other.positionX - hail.positionX;
            constants[i] = hail.positionZ * hail.velocityX - hail.positionX * hail.velocityZ +
                    other.positionX * other.velocityZ - other.positionZ * other.velocityX -
                    ((other.velocityZ - hail.velocityZ) * rockX) - ((hail.positionZ - other.positionZ) * vx);
        }

        gauss(matrix, constants);

        long rockZ = Math.round(constants[0]);

        return rockX + rockY + rockZ;
    }

    private static void gauss(double[][] matrix, double[] constants) {
        var n = matrix.length;
        for (int i = 0; i < n; i++) {
            var pivot = matrix[i][i];
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix[i][j] / pivot;
            }
            constants[i] = constants[i] / pivot;
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matrix[k][i];
                    for (int j = 0; j < n; j++) {
                        matrix[k][j] = matrix[k][j] - factor * matrix[i][j];
                    }
                    constants[k] = constants[k] - factor * constants[i];
                }
            }
        }
    }

    record Hail(long positionX, long positionY, long positionZ, long velocityX, long velocityY, long velocityZ) {
        double slope() {
            return (double) velocityY / velocityX;
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

        boolean isFuture(double x, double y) {
            return (velocityX >= 0 || positionX >= x) && (velocityX <= 0 || positionX <= x) &&
                    (velocityY >= 0 || positionY >= y) && (velocityY <= 0 || positionY <= y);
        }
    }
}
