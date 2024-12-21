package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;

public class Day20 extends Day {

    public static void main(String[] args) {
        Day day = new Day20();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Jurassic Jigsaw");
        List<String> input = this.readFile();
        Map<Integer, Tile> tiles = getTiles(input);
        this.setPart1(part1(tiles));
        this.setPart2(part2(tiles));
        this.printParts();
    }

    static long part1(Map<Integer, Tile> tiles) {
        return tiles.values().stream().filter(Tile::isCorner).mapToLong(Tile::getId).reduce(1, (a, b) -> a * b);
    }

    static long part2(Map<Integer, Tile> tiles) {
        int nbTiles = (int) sqrt(tiles.size());
        int[][] canvas = new int[nbTiles][nbTiles];
        int id = tiles.values().stream().filter(Tile::isTopLeftCorner).mapToInt(Tile::getId).min().orElseThrow();
        for (int i = 0; i < nbTiles; i++) {
            canvas[i][0] = id;
            for (int j = 1; j < nbTiles; j++) {
                int rightId = tiles.get(id).getRight();
                int topId = i == 0 ? 0 : canvas[i-1][j];
                position(tiles.get(rightId), topId, id);
                id = rightId;
                canvas[i][j] = id;
            }
            if (i < nbTiles - 1) {
                int bottomId = tiles.get(canvas[i][0]).getBottom();
                position(tiles.get(bottomId), canvas[i][0], 0);
                id = bottomId;
            }
        }

        int borderlessTileSize = tiles.get(id).getImage()[0].length - 2;
        int imageSize = borderlessTileSize * nbTiles;
        int[][] assembledImage = new int[imageSize][imageSize];
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas.length; j++) {
                int[][] tile = tiles.get(canvas[i][j]).getBorderlessTile();
                for (int x = 0; x < borderlessTileSize; x++) {
                    System.arraycopy(tile[x], 0, assembledImage[i * borderlessTileSize + x], j * borderlessTileSize, borderlessTileSize);
                }
            }
        }
        Tile image = new Tile(assembledImage);
        purgeImageFromSeaMonster(image);

        return Arrays.stream(image.getImage())
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    static Map<Integer, Tile> getTiles(List<String> input) {
        Map<Integer, Tile> tiles = new HashMap<>();

        List<Integer> indexes = IntStream.range(0, input.size())
                .filter(i -> "".equals(input.get(i)))
                .boxed()
                .collect(Collectors.toList());
        indexes.add(input.size());

        int prev = -1;
        for (int index: indexes) {
            int id = Integer.parseInt(input.get(prev+1).replaceAll("^.+(\\d{4}).$", "$1"));
            Tile newTile = new Tile(id, new ArrayList<>(input.subList(prev + 2, index)));
            tiles.put(id, newTile);
            prev = index;
        }
        for (Map.Entry<Integer, Tile> first : tiles.entrySet()) {
            for (Map.Entry<Integer, Tile> second : tiles.entrySet()) {
                if (!first.getKey().equals(second.getKey())) {
                    if (compareImages(first.getValue(), second.getValue())) {
                        first.getValue().setTop(second.getKey());
                        second.getValue().setBottom(first.getKey());
                    }
                }
            }
        }
        return tiles;
    }

    private static void purgeImageFromSeaMonster(Tile image) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (findSeaMonster(image.getImage()) > 0) {
                    return;
                }
                image.rotate();
            }
            image.flip();
        }
    }

    private static int findSeaMonster(int[][] image) {
        int count = 0;
        List<int[]> monster = Arrays.asList(new int[] {0,18},
                new int[] {1,0}, new int[] {1,5}, new int[] {1,6}, new int[] {1,11}, new int[] {1,12},
                new int[] {1,17}, new int[] {1,18}, new int[] {1,19},
                new int[] {2,1}, new int[] {2,4}, new int[] {2,7}, new int[] {2,10}, new int[] {2,13}, new int[] {2,16});
        for (int i = 0; i < image.length - 2; i++) {
            for (int j = 0; j < image.length - 19; j++) {
                boolean match = true;
                for (int[] monsterPart : monster) {
                    if (image[i+monsterPart[0]][j+monsterPart[1]] == 0) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int[] monsterPart : monster) {
                        image[i+monsterPart[0]][j+monsterPart[1]] = 0;
                    }
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean compareImages(Tile first, Tile second) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (Arrays.equals(first.topBorder(), second.bottomBorder())) {
                    return true;
                }
                first.rotate();
                if (Arrays.equals(first.topBorder(), second.bottomBorder())) {
                    return true;
                }
                first.rotate();
                if (Arrays.equals(first.topBorder(), second.bottomBorder())) {
                    return true;
                }
                first.rotate();
                if (Arrays.equals(first.topBorder(), second.bottomBorder())) {
                    return true;
                }
                first.flip();
            }
            second.rotate();
        }

        return false;
    }

    private static void position(Tile t, int top, int left) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (t.getTop() == top && t.getLeft() == left) {
                    return;
                }
                t.rotate();
                if (t.getTop() == top && t.getLeft() == left) {
                    return;
                }
                t.rotate();
                if (t.getTop() == top && t.getLeft() == left) {
                    return;
                }
                t.rotate();
                if (t.getTop() == top && t.getLeft() == left) {
                    return;
                }
                t.flip();
                if (t.getTop() == top && t.getLeft() == left) {
                    return;
                }
            }
        }
    }

    static class Tile {
        private final int id;
        private int[][] image;
        private final int size;
        private int top = 0;
        private int right = 0;
        private int bottom = 0;
        private int left = 0;

        public int[] topBorder() {
            return image[0];
        }

        public int[] bottomBorder() {
            return image[size - 1];
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getRight() {
            return right;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        public int getLeft() {
            return left;
        }

        public Tile(int id, List<String> input) {
            this.id = id;
            this.size = input.get(0).length();
            this.image = newTile();
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (input.get(i).charAt(j) == '#')
                        this.image[i][j] = 1;
                }
            }
        }

        public Tile(int[][] image) {
            this.id = 0;
            this.image = image;
            this.size = image[0].length;
        }

        public int getId() {
            return id;
        }

        public int[][] getImage() {
            return image;
        }

        public int getNbNeighbours() {
            int count = 0;
            if (top != 0) count++;
            if (right != 0) count++;
            if (bottom != 0) count++;
            if (left != 0) count++;
            return count;
        }

        public boolean isCorner() {
            return getNbNeighbours() == 2;
        }

        private int[][] newTile() {
            int[][] tile = new int[this.size][this.size];
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    tile[i][j] = 0;
                }
            }
            return tile;
        }

        public int[][] getBorderlessTile() {
            int[][] tile = new int[this.size - 2][this.size - 2];
            for (int i = 0; i < this.size - 2; i++) {
                System.arraycopy(this.image[i + 1], 1, tile[i], 0, this.size - 2);
            }
            return tile;
        }

        @SuppressWarnings("SuspiciousNameCombination")
        public void rotate() {
            int[][] ret = new int[this.size][this.size];

            for (int i = 0; i < this.size; i++)
                for (int j = 0; j < this.size; j++)
                    ret[i][j] = this.image[this.size - j - 1][i];

            this.image = ret;
            int topSav = this.top;
            this.top = this.left;
            this.left = this.bottom;
            this.bottom = this.right;
            this.right = topSav;
        }

        public void flip(){
            int[] temp;

            for (int i = 0; i < this.size / 2; i++){
                temp = this.image[this.size - i - 1];
                this.image[this.size - i - 1] = this.image[i];
                this.image[i] = temp;
            }
            int topSav = this.top;
            this.top = this.bottom;
            this.bottom = topSav;
        }

        public boolean isTopLeftCorner() {
            return this.top == 0 && this.left == 0;
        }

    }
}
