package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.anne.common.Direction.*;
import static org.anne.common.GridHelper.getCharGrid;

public class Day13 extends Day {
    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("Mine Cart Madness");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static String part1(List<String> input) {
        char[][] grid = getCharGrid(input);
        List<Cart> carts = getCarts(grid);

        while (true) {
            sortCarts(carts);
            for (int i = 0; i < carts.size(); i++) {
                Cart cart = carts.get(i).move(grid);
                carts.set(i, cart);
                for (int j = 0; j < carts.size(); j++) {
                    if (i != j && cart.isCollision(carts.get(j))) {
                        return cart.toString();
                    }
                }
            }
        }
    }

    public static String part2(List<String> input) {
        char[][] grid = getCharGrid(input);
        List<Cart> carts = getCarts(grid);

        while (carts.size() > 1) {
            sortCarts(carts);
            for (int i = 0; i < carts.size(); i++) {
                Cart cart = carts.get(i).move(grid);
                carts.set(i, cart);
                for (int j = 0; j < carts.size(); j++) {
                    if (i != j && !cart.crashed && !carts.get(j).crashed && cart.isCollision(carts.get(j))) {
                        carts.set(i, cart.crash());
                        carts.set(j, carts.get(j).crash());
                    }
                }
            }
            carts.removeIf(Cart::crashed);
        }
        return carts.getFirst().toString();
    }

    private static List<Cart> getCarts(char[][] grid) {
        List<Cart> carts = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                char c = grid[y][x];
                if (c == '^' || c == 'v' || c == '<' || c == '>') {
                    carts.add(new Cart(new Point(x, y), Direction.fromChar(c), 0, false));
                    grid[y][x] = (c == '^' || c == 'v') ? '|' : '-';
                }
            }
        }
        return carts;
    }

    private static void sortCarts(List<Cart> carts) {
        carts.sort((cart1, cart2) -> {
            if (cart1.p.y != cart2.p.y) {
                return Integer.compare(cart1.p.y, cart2.p.y);
            } else {
                return Integer.compare(cart1.p.x, cart2.p.x);
            }
        });
    }

    record Cart(Point p, Direction direction, int nextTurn, boolean crashed) {
        public Cart move(char[][] grid) {
            Point newPoint = direction.move(p);
            Direction newDirection = direction;
            int newNextTurn = nextTurn;

            switch (grid[newPoint.y][newPoint.x]) {
                case '/' -> newDirection = switch (direction) {
                    case NORTH -> EAST;
                    case SOUTH -> WEST;
                    case WEST -> SOUTH;
                    case EAST -> NORTH;
                };
                case '\\' -> newDirection = switch (direction) {
                    case NORTH -> WEST;
                    case SOUTH -> EAST;
                    case WEST -> NORTH;
                    case EAST -> SOUTH;
                };
                case '+' -> {
                    newDirection = switch (nextTurn) {
                        case 0 -> direction.rotate90(false);
                        case 2 -> direction.rotate90(true);
                        default -> newDirection;
                    };
                    newNextTurn = (nextTurn + 1) % 3;
                }
            }
            return new Cart(newPoint, newDirection, newNextTurn, crashed);
        }

        public boolean isCollision(Cart other) {
            return p.equals(other.p);
        }

        public Cart crash() {
            return new Cart(p, direction, nextTurn, true);
        }

        @Override
        public String toString() {
            return p.x + "," + p.y;
        }
    }
}
