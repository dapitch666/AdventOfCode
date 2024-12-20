package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.GridHelper;
import org.anne.common.Utils;

import java.awt.*;
import java.util.List;

public class Day20 extends Day {
    public static void main(String[] args) {
        Day day = new Day20();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Race Condition");
        List<String> input = this.readFile();
        this.setPart1(part1(input, 100));
        this.setPart2(part2(input, 100));
        this.printParts();
    }

    public static int part1(List<String> input, int minSavedTime) {
        return getCheats(input, 2, minSavedTime);
    }

    public static int part2(List<String> input, int minSavedTime) {
        return getCheats(input, 20, minSavedTime);
    }

    static int getCheats(List<String> input, int maxDistance, int minSavedTime) {
        var raceTrack = GridHelper.getCharGrid(input);
        Point start = GridHelper.findChar(raceTrack, 'S');
        Point end = GridHelper.findChar(raceTrack, 'E');
        var path = GridHelper.findShortestPath(raceTrack, start, end, c -> c != '#');
        int cheats = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            for (int j = i + 1; j < path.size(); j++) {
                int distance = (int) Utils.manhattanDistance(path.get(i), path.get(j));
                if (distance <= maxDistance && (j - i - distance) >= minSavedTime) {
                    cheats++;
                }
            }
        }
        return cheats;
    }
}
