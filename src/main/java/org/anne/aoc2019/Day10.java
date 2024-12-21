package org.anne.aoc2019;

import org.anne.common.Day;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day10 extends Day {

    public static void main(String[] args) {
        Day day = new Day10();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Monitoring Station");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    static int part1(List<String> input) {
        List<Point> asteroids = asteroidMap(input);
        int max = 0;
        for (Point asteroid : asteroids) {
            int count = visibleAsteroids(asteroids, asteroid).size();
            max = Math.max(count, max);
        }
        return max;
    }

    static int part2(List<String> input) {
        List<Point> asteroids = asteroidMap(input);
        int max = 0;
        Point monitoringStation = new Point();
        List<Point> visibleAsteroids = new ArrayList<>();
        for (Point asteroid : asteroids) {
            List<Point> visibles = new ArrayList<>(visibleAsteroids(asteroids, asteroid).values());
            if (visibles.size() > max) {
                max = visibles.size();
                monitoringStation = asteroid;
                visibleAsteroids = visibles;
            }
        }
        max = 200;
        while (max > 0) {
            if (visibleAsteroids.size() < max) {
                max -= visibleAsteroids.size();
                asteroids.removeAll(visibleAsteroids);
                asteroids = new ArrayList<>(visibleAsteroids(asteroids, monitoringStation).values());
            } else {
                Map<Double, Point> visibleMap = visibleAsteroids(asteroids, monitoringStation);
                List<Point> vaporizeCandidates = visibleMap.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .map(Map.Entry::getValue)
                        .toList();
                Point lastVaporizedAsteroid = vaporizeCandidates.get(199);
                return lastVaporizedAsteroid.x * 100 + lastVaporizedAsteroid.y;
            }
        }
        return 0;
    }

    public static List<Point> asteroidMap(List<String> input) {
        List<Point> asteroids = new ArrayList<>();
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                if(line.charAt(x) == '#') {
                    asteroids.add(new Point(x, y));
                }
            }
        }
        return asteroids;
    }

    static Map<Double, Point> visibleAsteroids (List<Point> asteroids, Point asteroid) {
        if (asteroid.x == 0 && asteroid.y == 2) {
            System.out.println();
        }
        Map<Double, Point> angles = new HashMap<>();
        for (Point point : asteroids) {
            if (!point.equals(asteroid)) {
                double distance = calculateDistance(asteroid, point);
                double angle = calculateAngle(asteroid, point);
                if (angles.containsKey(angle)) {
                    if (distance > calculateDistance(asteroid, angles.get(angle))) {
                        continue;
                    }
                }
                angles.put(angle, point);
            }
        }
        return angles;
    }

    public static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public static double calculateAngle(Point p1, Point p2) {
        double angle = Math.toDegrees(Math.atan2(p2.y - p1.y, p2.x - p1.x) + Math.PI / 2.0);
        return angle < 0 ? angle + 360 : angle;
    }
}
