package org.anne.aoc2017;

import org.anne.common.Day;
import org.anne.common.Point3d;

import java.util.*;
import java.util.stream.Collectors;

public class Day20 extends Day {
    public static void main(String[] args) {
        new Day20().run();
    }

    @Override
    public void execute() {
        setName("Particle Swarm");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        List<Particle> particles = input.stream().map(Particle::fromString).toList();
        return particles.indexOf(particles.stream()
                .min(Comparator.comparingInt(p -> p.distanceToOrigin(1000)))
                .orElseThrow());
    }

    public static int part2(List<String> input) {
        List<Particle> particles = new ArrayList<>(input.stream().map(Particle::fromString).toList());
        for (int t = 0; t < 50; t++) { // 50 is a large enough number that works for my input
            Map<Point3d, List<Integer>> positions = new HashMap<>();
            for (int i = 0; i < particles.size(); i++) {
                Point3d position = particles.get(i).positionAfter(t);
                positions.computeIfAbsent(position, k -> new ArrayList<>()).add(i);
            }
            Set<Integer> toRemove = positions.values().stream()
                    .filter(indices -> indices.size() > 1)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
            particles.removeIf(p -> toRemove.contains(particles.indexOf(p)));
        }
        return particles.size();
    }

    record Particle(Point3d position, Point3d velocity, Point3d acceleration) {
        static Particle fromString(String s) {
            String[] parts = s.split(", ");
            return new Particle(getPoint3d(parts[0]), getPoint3d(parts[1]), getPoint3d(parts[2]));
        }

        private static Point3d getPoint3d(String part) {
            String[] parts = part.substring(3, part.length() - 1).split(",");
            return new Point3d(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }

        public int distanceToOrigin(int tick) {
            return positionAfter(tick).absSum();
        }

        public Point3d positionAfter(int tick) {
            int x = position.x() + velocity.x() * tick + acceleration.x() * (tick * (tick + 1) / 2);
            int y = position.y() + velocity.y() * tick + acceleration.y() * (tick * (tick + 1) / 2);
            int z = position.z() + velocity.z() * tick + acceleration.z() * (tick * (tick + 1) / 2);
            return new Point3d(x, y, z);
        }
    }
}
