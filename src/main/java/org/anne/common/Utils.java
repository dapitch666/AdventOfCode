package org.anne.common;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
}
