package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.List;

public class Day24 extends Day {
    public static void main(String[] args) {
        new Day24().run();
    }

    @Override
    public void execute() {
        setName("Electromagnetic Moat");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        List<Component> components = input.stream()
                .map(line -> line.split("/"))
                .map(parts -> new Component(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .toList();
        return bestBridge(components, 0, 0, 0, new State(0, 0), false).maxStrength;
    }

    public static int part2(List<String> input) {
        List<Component> components = input.stream()
                .map(line -> line.split("/"))
                .map(parts -> new Component(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .toList();
        return bestBridge(components, 0, 0, 0, new State(0, 0), true).maxStrength;
    }

    private static State bestBridge(List<Component> components, int currentPort, int length, int strength, State state, boolean part2) {
        for (Component component : components) {
            if (component.hasPort(currentPort)) {
                component.used = true;
                state = bestBridge(components, component.getOtherPort(currentPort), length + 1, strength + component.portA + component.portB, state, part2);
                component.used = false;
            }
        }
        return new State(!part2 || length == state.maxLength ? Math.max(strength, state.maxStrength) : state.maxStrength, Math.max(length, state.maxLength));
    }

    static class Component {
        final int portA;
        final int portB;
        boolean used = false;

        Component(int portA, int portB) {
            this.portA = portA;
            this.portB = portB;
        }

        boolean hasPort(int port) {
            return !used & (portA == port || portB == port);
        }

        public int getOtherPort(int currentPort) {
            return portA == currentPort ? portB : portA;
        }
    }

    record State(int maxStrength, int maxLength) {
    }
}
