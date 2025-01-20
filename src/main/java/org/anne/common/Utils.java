package org.anne.common;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public static List<String> transpose(List<String> input) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.get(0).length(); i++) {
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

    public static long manhattanDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    
    public static long getArea(List<Point> vertices) {
        // shoelace formula + pick's theorem 
        // we also include boundaries
        if (!vertices.get(0).equals(vertices.get(vertices.size() - 1))) {
            vertices.add(vertices.get(0));
        }
        vertices.add(vertices.get(0));
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
