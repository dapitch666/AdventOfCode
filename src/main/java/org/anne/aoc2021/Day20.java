package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day20 extends Day {

    public static void main(String[] args) {
        Day day = new Day20();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Trench Map");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }


    public static int part1(List<String>  input) {
        String algorithm = input.get(0);
        int[][] image = getStartingImage(input);
        int def = 0;
        for (int j = 0; j < 2; j++) {
            image = enhanceImage(image, algorithm, def);
            def = algorithm.charAt(0) == '#' && j % 2 == 0 ? 1 : 0;
        }
        return Stream.of(image).flatMapToInt(IntStream::of).sum();
    }

    public static int part2(List<String>  input) {
        String algorithm = input.get(0);
        int[][] image = getStartingImage(input);
        int def = 0;
        for (int i = 0; i < 50; i++) {
            image = enhanceImage(image, algorithm, def);
            def = algorithm.charAt(0) == '#' && i % 2 == 0 ? 1 : 0;
        }
        return Stream.of(image).flatMapToInt(IntStream::of).sum();
    }

    static int[][] getStartingImage(List<String> input) {
        int[][] image = new int[input.size() - 2][input.get(2).length()];
        int i = 0;
        for (String line : input.subList(2, input.size())) {
            for (int j = 0; j < input.get(2).length(); j++) {
                if (line.charAt(j) == '#') {
                    image[i][j] = 1;
                }
            }
            i++;
        }
        return image;
    }

    static int[][] enhanceImage(int[][] image, String algorithm, int def) {
        int imageHeight = image.length + 2;
        int imageWidth = image[0].length + 2;
        int[][] enhancedImage = new int[imageHeight][imageWidth];
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                StringBuilder code = new StringBuilder();
                for (int ii = i-1; ii <= i+1; ii++) {
                    for (int jj = j-1; jj <= j+1; jj++) {
                        try {
                            code.append(image[ii - 1][jj - 1]);
                        } catch (IndexOutOfBoundsException e) {
                            code.append(def);
                        }
                    }
                }
                enhancedImage[i][j] = algorithm.charAt(Integer.parseInt(code.toString(), 2)) == '#' ? 1 : 0;
            }
        }
        return enhancedImage;
    }
}
