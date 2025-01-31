package org.anne.aoc2017;

import org.anne.common.Day;
import java.util.List;

public class Day13 extends Day {
    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("Packet Scanners");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        return input.stream()
                .map(line -> line.split(": "))
                .map(Layer::new)
                .filter(layer -> layer.caught(0))
                .mapToInt(layer -> layer.depth * layer.range)
                .sum();
    }

    public static int part2(List<String> input) {
        List<Layer> layers = input.stream()
                .map(line -> line.split(": "))
                .map(Layer::new)
                .toList();
        int time = 0;
        while (true) {
            boolean caught = false;
            for (Layer layer : layers) {
                if (layer.caught(time)) {
                    caught = true;
                    break;
                }
            }
            if (!caught) {
                return time;
            }
            time++;
        }
    }

    record Layer(int depth, int range) {
        Layer(String[] parts) {
            this(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        public boolean caught(int time) {
            return (depth + time) % (2 * (range - 1)) == 0;
        }
    }
}