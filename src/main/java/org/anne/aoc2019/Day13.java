package org.anne.aoc2019;

import org.anne.common.Day;

import java.util.List;

public class Day13 extends Day {

    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("Care Package");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    static int part1(String input) {
        int tiles = 0;
        Computer computer = new Computer(input);
        computer.compute();
        List<Long> output = computer.getOutputs();
        for (int i = 0; i < output.size(); i += 3) {
            if (output.get(i + 2) == 2) {
                tiles++;
            }
        }
        return tiles;
    }

    static int part2(String input) {
        input = "2" + input.substring(1);
        Computer computer = new Computer(input);
        computer.compute();
        Game game = getGame(computer);
        while (computer.isStillRunning()) {
            computer.writeInput(Integer.compare(game.ball, game.paddle));
            computer.compute();
            game = getGame(computer);
        }
        return game.score;
    }

    static Game getGame(Computer computer) {
        int ball = 0;
        int paddle = 0;
        int score = 0;
        List<Long> output = computer.getOutputs();
        while (!output.isEmpty()) {
            int x = Math.toIntExact(output.removeFirst());
            int y = Math.toIntExact(output.removeFirst());
            int tile = Math.toIntExact(output.removeFirst());
            if (x == -1 && y == 0) {
                score = tile;
            } else {
                switch (tile) {
                    case 3 -> paddle = x;
                    case 4 -> ball = x;
                }
            }
        }
        return new Game(ball, paddle, score);
    }

    record Game(int ball, int paddle, int score) {}
}
