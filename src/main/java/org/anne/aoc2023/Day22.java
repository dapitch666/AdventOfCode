package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Point3d;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Day22 extends Day {
    public static void main(String[] args) {
        Day day = new Day22();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static int part1(List<String> input) {
        var bricks = input.stream().map(Brick::new).sorted().toList();
        var support = new HashMap<Brick, List<Brick>>();
        var isSupportedBy = new HashMap<Brick, List<Brick>>();
        var occupied = new HashSet<Point3d>();
        var disintegratable = new HashSet<Brick>();
        for (var brick : bricks) {
            brick.drop(occupied);
            occupied.addAll(brick.getOccupied());
            //System.out.println(brick + " " + brick.getOccupied());
        }
        for (var brick : bricks) {
            for (var other : bricks.stream().filter(b -> b.p1.z() == brick.p2.z() + 1).toList()) {
                if (brick.supports(other)) {
                    support.computeIfAbsent(brick, b -> new ArrayList<>()).add(other);
                    isSupportedBy.computeIfAbsent(other, b -> new ArrayList<>()).add(brick);
                }
            }
        }
        //support.forEach((k, v) -> System.out.println(k.id + " supports " + v.stream().map(b -> b.id).toList()));
        //isSupportedBy.forEach((k, v) -> System.out.println(k.id + " is supported by " + v.stream().map(b -> b.id).toList()));
        for (var brick : bricks) {
            if (!support.containsKey(brick)) {
                disintegratable.add(brick);
            } else if (support.get(brick).stream().allMatch(b -> isSupportedBy.get(b).size() > 1)) {
                disintegratable.add(brick);
            }
        }
        return disintegratable.size();
    }

    public static int part2(List<String> input) {
        return 0;
    }
    
    static class Brick implements Comparable<Brick> {
        Point3d p1;
        Point3d p2;
        String id;
        
        public Brick (String s) {
            String[] split = s.split("~");
            String[] p1 = split[0].split(",");
            String[] p2 = split[1].split(",");
            this.p1 = new Point3d(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2]));
            this.p2 = new Point3d(Integer.parseInt(p2[0]), Integer.parseInt(p2[1]), Integer.parseInt(p2[2]));
            //this.id = split[2];
        }

        public Brick(Point3d p1, Point3d p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public int compareTo(@NotNull Day22.Brick o) {
            return Integer.compare(p1.z(), o.p1.z());
        }
        
        public boolean supports(Brick other) {
            var newBrick = new Brick(other.p1.add(new Point3d(0, 0, -1)), other.p2.add(new Point3d(0, 0, -1)));
            return newBrick.getOccupied().stream().anyMatch(p -> this.getOccupied().contains(p));
        }
        
        public boolean isSupportedBy(Brick other) {
            return other.supports(this);
        }
        
        public List<Point3d> getOccupied() {
            List<Point3d> occupied = new ArrayList<>();
            for (int x = p1.x(); x <= p2.x(); x++) {
                for (int y = p1.y(); y <= p2.y(); y++) {
                    for (int z = p1.z(); z <= p2.z(); z++) {
                        occupied.add(new Point3d(x, y, z));
                    }
                }
            }
            return occupied;
        }
        
        public void drop(HashSet<Point3d> occupied) {
            var newBrick = new Brick(p1.add(new Point3d(0, 0, -1)), p2.add(new Point3d(0, 0, -1)));
            if (newBrick.p1.z() > 0 && occupied.stream().noneMatch(p -> newBrick.getOccupied().contains(p))) {
                this.p1 = newBrick.p1;
                this.p2 = newBrick.p2;
                this.drop(occupied);
            }
        }

        @Override
        public String toString() {
            return this.id + "(" + this.p1.toString() + "~" + this.p2.toString() + ")";
        }
    }
}
