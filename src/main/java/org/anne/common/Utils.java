package org.anne.common;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.anne.common.Constants.LINE_SEPARATOR;

public class Utils {

    public static <T> T last(T[] array) {
        return array[array.length - 1];
    }

    public static String print2dIntArray(int[][] array, String empty, String full) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                sb.append(ints[j] == 0 ? empty : full);
            }
            sb.append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    public static String print2dIntArray(int[][] array) {
        return print2dIntArray(array, " ", "#");
    }

    public static String print2dArray(char[][] array) {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : array) {
            for (int j = 0; j < array[0].length; j++) {
                sb.append(chars[j]);
            }
            sb.append(LINE_SEPARATOR);
        }
        return sb.toString();
    }
    
    public static String printAscii(int[][] array) {
        // print2dIntArray(array, "  ", "██");
        return print2dIntArray(array, "  ", "##");
    }

    public static String printAscii(Set<Point> points) {
        int minX = points.stream().map(x -> x.x).min(Integer::compare).orElseThrow();
        int maxX = points.stream().map(x -> x.x).max(Integer::compare).orElseThrow();
        int minY = points.stream().map(x -> x.y).min(Integer::compare).orElseThrow();
        int maxY = points.stream().map(x -> x.y).max(Integer::compare).orElseThrow();
        int[][] array = new int[maxY - minY + 1][maxX - minX + 1];
        for (Point p : points) {
            array[p.y - minY][p.x - minX] = 1;
        }
        return printAscii(array);
    }

    public static int[][] getArray(Set<Point> points) {
        int minX = points.stream().map(x -> x.x).min(Integer::compare).orElseThrow();
        int maxX = points.stream().map(x -> x.x).max(Integer::compare).orElseThrow();
        int minY = points.stream().map(x -> x.y).min(Integer::compare).orElseThrow();
        int maxY = points.stream().map(x -> x.y).max(Integer::compare).orElseThrow();
        int[][] array = new int[maxY - minY + 1][maxX - minX + 1];
        for (Point p : points) {
            array[p.y - minY][p.x - minX] = 1;
        }
        return array;
    }

    public static String printAscii(Set<Point> points, String empty, String full) {
        int minX = points.stream().map(x -> x.x).min(Integer::compare).orElseThrow();
        int maxX = points.stream().map(x -> x.x).max(Integer::compare).orElseThrow();
        int minY = points.stream().map(x -> x.y).min(Integer::compare).orElseThrow();
        int maxY = points.stream().map(x -> x.y).max(Integer::compare).orElseThrow();
        int[][] array = new int[maxY - minY + 1][maxX - minX + 1];
        for (Point p : points) {
            array[p.y - minY][p.x - minX] = 1;
        }
        return print2dIntArray(array, empty, full);
    }

    public static String printHashmap(Map<Point, Character> map) {
        int minX = map.keySet().stream().map(x -> x.x).min(Integer::compare).orElseThrow();
        int maxX = map.keySet().stream().map(x -> x.x).max(Integer::compare).orElseThrow();
        int minY = map.keySet().stream().map(x -> x.y).min(Integer::compare).orElseThrow();
        int maxY = map.keySet().stream().map(x -> x.y).max(Integer::compare).orElseThrow();
        char[][] array = new char[maxY - minY + 1][maxX - minX + 1];
        for (Point p : map.keySet()) {
            array[p.y - minY][p.x - minX] = map.get(p);
        }
        StringBuilder sb = new StringBuilder();
        for (char[] chars : array) {
            for (int j = 0; j < array[0].length; j++) {
                sb.append(chars[j]);
            }
            sb.append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    /**
     * Converts a 2D array of integers representing a grid of letters into a string using OCR.
     *
     * @param array the 2D array of integers representing the grid of letters
     * @param letterWidth the width of each letter in the grid (including letter spacing)
     * @param letterHeight the height of each letter in the grid
     * @return the string representation of the letters in the grid
     */
    public static String ocr(int[][] array, int letterWidth, int letterHeight) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < array.length; y += letterHeight) {
            for (int x = 0; x < array[0].length; x += letterWidth) {
                int val = 0;
                for (int i = 0; i < letterHeight; i++) {
                    for (int j = 0; j < letterWidth; j++) {
                        if (x + j >= array[0].length) {
                            continue;
                        }
                        val |= array[y + i][x + j] << (letterWidth * letterHeight - 1 - i * letterWidth - j);
                    }
                }
                System.out.println("0x" + Integer.toHexString(val).toUpperCase());
                if (letterWidth == 5 && letterHeight == 6) {
                    sb.append(Letter.valueOf5x6(val));
                }
                if (letterWidth == 8 && letterHeight == 10) {
                    sb.append(Letter.valueOf8x10(val));
                }
            }
        }
        return sb.toString();
    }

    public static List<String> transpose(List<String> input) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.getFirst().length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (String s : input) {
                try {
                    sb.append(s.charAt(i));
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(' ');
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    /**
    * Converts a string containing integers into an IntStream.
    *
    * This method uses a regular expression to find all integer values (including negative numbers)
    * in the input string and returns them as an IntStream.
    *
    * @param input the input string containing integers
    * @return an IntStream of integers found in the input string
    */
    public static IntStream inputToIntStream(String input) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.results().map(MatchResult::group).mapToInt(Integer::parseInt);
    }

    public static String repeat(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }

    static boolean containsNewLine(String str) {
        Pattern regex = Pattern.compile("^(.*)$", Pattern.MULTILINE);
        return regex.split(str).length > 0;
    }

    public static int manhattanDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public static int manhattanDistance(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("a and b must have the same size");
        }
        return IntStream.range(0, a.length).map(i -> Math.abs(a[i] - b[i])).sum();
    }
    
    public static long getArea(List<Point> vertices) {
        // shoelace formula + pick's theorem 
        // we also include boundaries
        if (!vertices.getFirst().equals(vertices.getLast())) {
            vertices.add(vertices.getFirst());
        }
        vertices.add(vertices.getFirst());
        var area = 0L;
        var boundaries = 0L;
        for (int i = 1; i < vertices.size() - 1; i++) {
            area += (long) vertices.get(i).x * (vertices.get(i - 1).y - vertices.get(i + 1).y);
            boundaries += manhattanDistance(vertices.get(i), vertices.get(i-1));
        }
        return Math.abs(area / 2) + boundaries / 2 + 1;
    }

    public static List<String> match(Pattern pattern, String string) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            List<String> result = new ArrayList<>();
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result.add(matcher.group(i));
            }
            return result;
        }
        return List.of();
    }
}
