package org.anne.aoc2022;

import org.anne.common.Day;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Day22 extends Day {
    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Monkey Map");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        return getPassword(input, true);
    }

    public static int part2(List<String> input) {
        return getPassword(input, false);
    }

    public static int getPassword(List<String> input, boolean isPart1) {
        var instructions = Arrays.stream(input.get(input.size() - 1)
                        .replace("R", " R ")
                        .replace("L", " L ")
                        .split(" "))
                        .toList();
        Map<Point, Integer> map = new HashMap<>();
        for (int y = 0; y < input.size() - 2; y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                switch (line.charAt(x)) {
                    case '.' -> map.put(new Point(x, y), 0);
                    case '#' -> map.put(new Point(x, y), 1);
                    default -> {
                    }
                }
            }
        }

        Point current = map.keySet().stream().filter(p -> p.y == 0).min(Comparator.comparingInt(p -> p.x)).orElseThrow();
        Direction direction = Direction.RIGHT;

        for (String instruction : instructions) {
            if (List.of("R", "L").contains(instruction)) {
                direction = direction.rotate(instruction);
            } else {
                for (int i = 0; i < Integer.parseInt(instruction); i++) {
                    Point next = new Point(current.x, current.y);
                    next.translate(direction.x, direction.y);
                    Direction newDirection = direction;
                    if (!map.containsKey(next)) {
                        if (isPart1) {
                            int col = next.x;
                            int row = next.y;
                            switch (direction) {
                                case RIGHT -> next.setLocation(map.keySet().stream()
                                        .filter(p -> p.y == row)
                                        .min(Comparator.comparingInt(p -> p.x))
                                        .orElseThrow());
                                case DOWN -> next.setLocation(map.keySet().stream()
                                        .filter(p -> p.x == col)
                                        .min(Comparator.comparingInt(p -> p.y))
                                        .orElseThrow());
                                case LEFT -> next.setLocation(map.keySet().stream()
                                        .filter(p -> p.y == row)
                                        .max(Comparator.comparingInt(p -> p.x))
                                        .orElseThrow());
                                case UP -> next.setLocation(map.keySet().stream()
                                        .filter(p -> p.x == col)
                                        .max(Comparator.comparingInt(p -> p.y))
                                        .orElseThrow());
                            }
                        } else {
                            int curRegion = getRegion(current);
                            switch (curRegion) {
                                case 0 -> {
                                    if (direction == Direction.UP) {
                                        next.setLocation(0, current.x + 100);
                                        newDirection = Direction.RIGHT;
                                    } else if (direction == Direction.LEFT) {
                                        next.setLocation(0, 149 - current.y);
                                        newDirection = Direction.RIGHT;
                                    }
                                }
                                case 1 -> {
                                    if (direction == Direction.UP) {
                                        next.setLocation(current.x - 100, 199);
                                    } else if (direction == Direction.RIGHT) {
                                        next.setLocation(99, 149 - current.y);
                                        newDirection = Direction.LEFT;
                                    } else if (direction == Direction.DOWN) {
                                        next.setLocation(99, current.x - 50);
                                        newDirection = Direction.LEFT;
                                    }
                                }
                                case 2 -> {
                                    if (direction == Direction.RIGHT) {
                                        next.setLocation(current.y + 50, 49);
                                        newDirection = Direction.UP;
                                    } else if (direction == Direction.LEFT) {
                                        next.setLocation(current.y - 50, 100);
                                        newDirection = Direction.DOWN;
                                    }
                                }
                                case 3 -> {
                                    if (direction == Direction.UP) {
                                        next.setLocation(50, current.x + 50);
                                        newDirection = Direction.RIGHT;
                                    } else if (direction == Direction.LEFT) {
                                        next.setLocation(50, 149 - current.y);
                                        newDirection = Direction.RIGHT;
                                    }
                                }
                                case 4 -> {
                                    if (direction == Direction.RIGHT) {
                                        next.setLocation(149, 149 - current.y);
                                        newDirection = Direction.LEFT;
                                    } else if (direction == Direction.DOWN) {
                                        next.setLocation(49, current.x + 100);
                                        newDirection = Direction.LEFT;
                                    }
                                }
                                case 5 -> {
                                    if (direction == Direction.RIGHT) {
                                        next.setLocation(current.y - 100, 149);
                                        newDirection = Direction.UP;
                                    } else if (direction == Direction.DOWN) {
                                        next.setLocation(current.x + 100, 0);
                                    } else if (direction == Direction.LEFT) {
                                        next.setLocation(current.y - 100, 0);
                                        newDirection = Direction.DOWN;
                                    }
                                }
                            }
                        }
                    }
                    if (map.get(next) == 1) {
                        break;
                    }
                    current = next;
                    direction = newDirection;
                }
            }
        }

        return 1000 * (current.y + 1) + 4 * (current.x + 1) + direction.value;
    }

    private enum Direction {
        RIGHT(0, 1, 0),
        DOWN(1, 0, 1),
        LEFT(2, -1, 0),
        UP(3, 0, -1);

        public final int value;
        public final int x;
        public final int y;

        Direction(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        public Direction directionOfValue(int value) {
            return switch (value) {
                case 0 -> Direction.RIGHT;
                case 1 -> Direction.DOWN;
                case 2 -> Direction.LEFT;
                case 3 -> Direction.UP;
                default -> throw new IllegalStateException("Unexpected value: " + value);
            };
        }

        public Direction rotate(String instruction) {
            int val = this.value + (instruction.equals("R") ? 1 : -1);
            if (val == 4) {
                val = 0;
            } else if (val == -1) {
                val = 3;
            }
            return directionOfValue(val);
        }
    }

    private static int getRegion(Point c) {
        if (c.x > 49 && c.x < 100 && c.y < 50) {
            return 0;
        }
        if (c.x > 99 && c.y < 50) {
            return 1;
        }
        if (c.y > 49 && c.y < 100) {
            return 2;
        }
        if (c.x < 50 && c.y > 99 && c.y < 150) {
            return 3;
        }
        if (c.x > 49 && c.x < 100 && c.y < 150) {
            return 4;
        }
        return 5;
    }
}
