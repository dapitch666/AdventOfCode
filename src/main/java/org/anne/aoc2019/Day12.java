package org.anne.aoc2019;

import org.anne.common.Day;
import org.anne.common.Point3d;

import java.util.ArrayList;
import java.util.List;

public class Day12  extends Day {

    public static void main(String[] args) {
        Day day = new Day12();
        day.setName("The N-Body Problem");
        List<String> input = day.readFile();
        day.setPart1(part1(input, 1000));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int part1(List<String> input, int steps) {
        List<Moon> moons = parseInput(input);
        for (int i = 0; i < steps; i++) {
            for (Moon moon : moons) {
                for (Moon other : moons) {
                    if (moon == other) continue;
                    moon.applyGravity(other);
                }
            }
            for (Moon moon : moons) {
                moon.applyVelocity();
            }
        }
        return moons.stream().map(Moon::getEnergy).reduce(0, Integer::sum);
    }

    static long part2(List<String> input) {
        List<Moon> moons = parseInput(input);

        long cycleX = 0;
        long cycleY = 0;
        long cycleZ = 0;
        long steps = 0;

        while (cycleX == 0 || cycleY == 0 || cycleZ == 0) {
            for (Moon moon : moons) {
                for (Moon other : moons) {
                    if (moon == other) continue;
                    moon.applyGravity(other);
                }
            }
            for (Moon moon : moons) {
                moon.applyVelocity();
            }
            steps++;
            if (cycleX == 0 && moons.stream().allMatch(moon -> moon.velocity.x() == 0)) {
                cycleX = steps;
            }
            if (cycleY == 0 && moons.stream().allMatch(moon -> moon.velocity.y() == 0)) {
                cycleY = steps;
            }
            if (cycleZ == 0 && moons.stream().allMatch(moon -> moon.velocity.z() == 0)) {
                cycleZ = steps;
            }
        }
        return lcm(lcm(cycleX, cycleY), cycleZ) * 2;
    }

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    static List<Moon> parseInput(List<String> input) {
        List<Moon> moons = new ArrayList<>();
        for (String line : input) {
            String[] parts = line.substring(1, line.length() - 1).split(",");
            int x = Integer.parseInt(parts[0].split("=")[1]);
            int y = Integer.parseInt(parts[1].split("=")[1]);
            int z = Integer.parseInt(parts[2].split("=")[1]);
            moons.add(new Moon(new Point3d(x, y, z)) );
        }
        return moons;
    }

    static class Moon {
        private Point3d position;
        private Point3d velocity;

        public Moon(Point3d position) {
            this.position = position;
            this.velocity = new Point3d(0, 0, 0);
        }

        public void applyGravity(Moon other) {
            int x = Integer.compare(other.position.x(), position.x());
            int y = Integer.compare(other.position.y(), position.y());
            int z = Integer.compare(other.position.z(), position.z());
            velocity = velocity.add(new Point3d(x, y, z));
        }

        public void applyVelocity() {
            position = position.add(velocity);
        }

        public int getEnergy() {
            return position.absSum() * velocity.absSum();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Moon other && other.position.equals(position) && other.velocity.equals(velocity);
        }
    }
}
