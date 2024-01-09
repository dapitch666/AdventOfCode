package org.anne.aoc2019;

import org.anne.common.Day;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class Day18 extends Day {

    static Set<Point> walls;
    static List<Point> keys;
    static Map<Point, Point> doors;
    static Map<Path, DistanceAndKeys> pathMap;
    static int keysBitmask;
    static int bestDistance;

    public static void main(String[] args) {
        Day day = new Day18();
        day.setName("Many-Worlds Interpretation");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(new ArrayList<>(input)));
        day.printParts();
    }

    static int part1(List<String> input) {
        return getTotalDistance(input);
    }

    static int part2(List<String> input) {
        Point middle = new Point(input.get(0).length() / 2, input.size() / 2);
        input.set(middle.y - 1, input.get(middle.y - 1).substring(0, middle.x - 1) + "@#@"
                + input.get(middle.y - 1).substring(middle.x + 2));
        input.set(middle.y, input.get(middle.y).substring(0, middle.x - 1) + "###"
                + input.get(middle.y).substring(middle.x + 2));
        input.set(middle.y + 1, input.get(middle.y + 1).substring(0, middle.x - 1) + "@#@"
                + input.get(middle.y + 1).substring(middle.x + 2));
        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        for (String line : input) {
            left.add(line.substring(0, middle.x + 1));
            right.add(line.substring(middle.x));
        }
        return getTotalDistance(left.subList(0, middle.y + 1)) + getTotalDistance(left.subList(middle.y, left.size()))
                + getTotalDistance(right.subList(0, middle.y + 1)) + getTotalDistance(right.subList(middle.y, right.size()));
    }

    private static int getTotalDistance(List<String> vault) {
        walls = new HashSet<>();
        doors = new HashMap<>();
        keys = new ArrayList<>();
        pathMap = new HashMap<>();
        Point start = new Point();

        Map<Point, Character> doorsAndKeysTemp = new HashMap<>();

        for (int y = 0; y < vault.size(); y++) {
            String line = vault.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == '#') {
                    walls.add(new Point(x, y));
                } else if (Character.isLetter(c)) {
                    doorsAndKeysTemp.put(new Point(x, y), line.charAt(x));
                    if (Character.isUpperCase(c)) {
                        if (vault.stream().anyMatch(s -> s.contains(Character.toString(Character.toLowerCase(c))))) {
                            doors.put(new Point(x, y), new Point());
                        }
                    } else {
                        keys.add(new Point(x, y));
                    }
                } else if (c == '@') {
                    start = new Point(x, y);
                }
            }
        }

        for (Point door : doors.keySet()) {
            char key = Character.toLowerCase(doorsAndKeysTemp.get(door));
            Point keyLoc = doorsAndKeysTemp.entrySet().stream()
                    .filter(e -> e.getValue() == key)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseThrow();
            doors.put(door, keyLoc);
        }

        keysBitmask = (1 << keys.size()) - 1;
        pathMap = new HashMap<>();
        bestDistance = Integer.MAX_VALUE;
        return distanceToCollectKeys(new State(start,0,0), new HashMap<>());
    }

    public static int distanceToCollectKeys(State current, Map<State, Integer> visited) {
        int MAXVALUE = 20000;
        if(current.keys == keysBitmask) {
            bestDistance = Math.min(current.distance, bestDistance);
            return 0;
        }
        if(current.distance > bestDistance || current.distance >= MAXVALUE) {
            return MAXVALUE;
        }
        if(visited.containsKey(current)) {
            return visited.get(current);
        }
        int result = MAXVALUE;
        for(int i = 0; i < keys.size(); i++) {
            if((current.keys & (1 << i)) != (1 << i)) {
                DistanceAndKeys distAndKeys;
                Path path = new Path(current.position, keys.get(i));
                if (pathMap.containsKey(path)) {
                    distAndKeys = pathMap.get(path);
                } else {
                    distAndKeys = getPath(current.position, keys.get(i), current.keys);
                    pathMap.put(path, distAndKeys);
                }
                if (distAndKeys == null || distAndKeys.distance > result || !((current.keys & distAndKeys.keys) == distAndKeys.keys)) {
                    continue;
                }
                State newState = new State(keys.get(i), (current.keys | (1 << i)), current.distance + distAndKeys.distance);
                result = Math.min(result, distAndKeys.distance + distanceToCollectKeys(newState, visited));
            }
        }
        visited.put(current,result);
        return result;
    }

    static DistanceAndKeys getPath(Point start, Point end, int curKeys) {
        int keysNeeded = 0;
        Map<Point, Point> parent = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Integer> currentCost = new HashMap<>();
        currentCost.put(start, 0);
        queue.add(start);
        List<Point> path = null;
        while(!queue.isEmpty()) {
            Point current = queue.poll();
            if(current.equals(end)) {
                path = new ArrayList<>();
                while(parent.containsKey(current)) {
                    path.add(new Point(current));
                    current = parent.get(current);
                }
                Collections.reverse(path);
                break;
            }
            for(Point point : neighbors(current)) {
                if(!walls.contains(point) && (!keys.contains(point) || point.equals(end) || (curKeys & (1 << keys.indexOf(point))) == (1 << keys.indexOf(point)))) {
                    int cost = currentCost.get(current) + 1;
                    if(cost < currentCost.getOrDefault(point, Integer.MAX_VALUE)) {
                        currentCost.put(point, cost);
                        parent.put(point, current);
                        queue.add(point);
                    }
                }
            }
        }
        if(path == null) {
            return null;
        }

        for(Point point : path) {
            if(doors.containsKey(point)) {
                keysNeeded = keysNeeded | (1 << keys.indexOf(doors.get(point)));
            }
        }
        return new DistanceAndKeys(path.size(), keysNeeded);
    }

    static List<Point> neighbors(Point point) {
        return Arrays.asList(
                new Point(point.x, point.y - 1),
                new Point(point.x, point.y + 1),
                new Point(point.x - 1, point.y),
                new Point(point.x + 1, point.y));
    }

    public record State(Point position, int keys, int distance) {}
    record Path(Point start, Point end) {}
    record DistanceAndKeys(int distance, int keys) {}
}
