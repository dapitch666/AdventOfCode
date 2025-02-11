package org.anne.aoc2021;

import org.anne.common.Day;
import org.anne.common.Point3d;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 extends Day {

    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Reactor Reboot");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<String>  input) {
        List<Cuboid> cuboids = cuboidList(input);
        Set<Point3d> onCubes = new HashSet<>();
        for (Cuboid cuboid : cuboids) {
            for (int x = Math.max(cuboid.xMin, -50); x <= Math.min(cuboid.xMax, 50); x++) {
                for (int y = Math.max(cuboid.yMin, -50); y <= Math.min(cuboid.yMax, 50); y++) {
                    for (int z = Math.max(cuboid.zMin, -50); z <= Math.min(cuboid.zMax, 50); z++) {
                        if (cuboid.isOn) {
                            onCubes.add(new Point3d(x, y, z));
                        } else {
                            onCubes.remove(new Point3d(x, y, z));
                        }
                    }
                }
            }
        }
        return onCubes.size();
    }

    public static long part2(List<String>  input) {
        List<Cuboid> cuboids = cuboidList(input);
        List<Cuboid> cuboidList = new ArrayList<>();
        for (Cuboid cuboid : cuboids) {
            List<Cuboid> done = new ArrayList<>();
            if (cuboid.isOn) {
                done.add(cuboid);
            }
            for (Cuboid c : cuboidList) {
                Optional<Cuboid> intersection = c.intersection(cuboid, !c.isOn);
                intersection.ifPresent(done::add);
            }
            cuboidList.addAll(done);
        }
        return cuboidList.stream().mapToLong(Cuboid::nbOnCubes).sum();
    }

    static List<Cuboid> cuboidList (List<String> input) {
        List<Cuboid> cuboids = new ArrayList<>();
        for (String line : input) {
            String p = "(on|off) x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)";
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(line);
            String command = "";
            int xMin = 0;
            int xMax = 0;
            int yMin = 0;
            int yMax = 0;
            int zMin = 0;
            int zMax = 0;
            while (matcher.find()) {
                command = matcher.group(1);
                xMin = Integer.parseInt(matcher.group(2));
                xMax = Integer.parseInt(matcher.group(3));
                yMin = Integer.parseInt(matcher.group(4));
                yMax = Integer.parseInt(matcher.group(5));
                zMin = Integer.parseInt(matcher.group(6));
                zMax = Integer.parseInt(matcher.group(7));
            }
            cuboids.add(new Cuboid(xMin, xMax, yMin, yMax, zMin, zMax, command.equals("on")));
        }
        return cuboids;
    }

    public record Cuboid(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax, boolean isOn) {

        public Optional<Cuboid> intersection(Cuboid o, boolean on) {
            if (xMin > o.xMax || xMax < o.xMin || yMin > o.yMax || yMax < o.yMin || zMin > o.zMax || zMax < o.zMin) {
                return Optional.empty();
            }

            return Optional.of(new Cuboid(
                    Math.max(xMin, o.xMin), Math.min(xMax, o.xMax), Math.max(yMin, o.yMin), Math.min(yMax, o.yMax),
                    Math.max(zMin, o.zMin), Math.min(zMax, o.zMax), on));
        }

        public long nbOnCubes() {
            return (xMax - xMin + 1L) * (yMax - yMin + 1L) * (zMax - zMin + 1L) * (isOn ? 1L : -1L);
        }
    }
}
