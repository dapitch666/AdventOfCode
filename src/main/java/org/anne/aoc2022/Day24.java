package org.anne.aoc2022;

import org.anne.common.Day;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class Day24 extends Day{
    public static void main(String[] args) {
        Day day = new Day24();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int maxX, maxY;
    static Point start, end;
    static HashSet<Point> bounds;
    static ArrayList<Blizzard> blizzards;

    public static int part1(List<String> input) {
        init(input);
        return run(start, end);
    }

    public static int part2(List<String> input) {
        init(input);
        return run(start, end) + run(end, start) + run(start, end);
    }

    static int run (Point start, Point end) {
        int steps = 0;
        Set<Point> currents = new HashSet<>();
        currents.add(start);

        do {
            blizzards.forEach(Blizzard::tick);
            Set<Point> blizzardsPositions = blizzards.stream().map(b -> b.pos).collect(Collectors.toSet());
            Set<Point> next = new HashSet<>();
            for(Point current : currents) {
                for(Point neighbours : Arrays.asList(
                        new Point(current.x - 1, current.y),
                        new Point(current.x + 1, current.y),
                        new Point(current.x, current.y - 1),
                        new Point(current.x, current.y + 1))) {
                    if (!bounds.contains(neighbours) && !blizzardsPositions.contains(neighbours))
                        next.add(neighbours);
                }
                if(!bounds.contains(current) && !blizzardsPositions.contains(current))
                    next.add(current);
            }
            currents = next;
            steps++;
        } while(!currents.contains(end));
        return steps;
    }

    static void init(List<String> input) {
        blizzards = new ArrayList<>();
        bounds = new HashSet<>();
        for(int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for(int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (!(c == '.')) {
                    if (line.charAt(x) == '#') bounds.add(new Point(x, y));
                    else blizzards.add(new Blizzard(new Point(x, y), c));
                }
            }
        }
        maxX = input.get(0).length() - 1;
        maxY = input.size() - 1;
        start = new Point(1, 0);
        end = new Point(maxX - 1, maxY);

        bounds.add(new Point(0,-1));
        bounds.add(new Point(1,-1));
        bounds.add(new Point(2,-1));
        bounds.add(new Point(maxX,maxY + 1));
        bounds.add(new Point(maxX -1,maxY + 1));
        bounds.add(new Point(maxX - 2,maxY + 1));
    }

    public record Blizzard (Point pos, char facing) {
        void tick() {
            Point newPos = switch (facing) {
                case '>' -> new Point(pos.x+1, pos.y);
                case 'v' -> new Point(pos.x, pos.y+1);
                case '<' -> new Point(pos.x-1, pos.y);
                case '^' -> new Point(pos.x, pos.y-1);
                default -> throw new IllegalStateException("Unexpected value: " + pos);
            };
            if(bounds.contains(newPos)) {
                switch (this.facing) {
                    case '^' -> this.pos.y = maxY - 1;
                    case 'v' -> this.pos.y = 1;
                    case '>' -> this.pos.x = 1;
                    case '<' -> this.pos.x = maxX - 1;
                }
            } else {
                this.pos.x = newPos.x;
                this.pos.y = newPos.y;
            }
        }
    }
}
