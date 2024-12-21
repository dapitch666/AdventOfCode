package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day25 extends Day {
    public static void main(String[] args) {
        Day day = new Day25();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Snowverload");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2());
        this.printParts();
    }


    public static int part1(List<String> input) {
        while (true) {
            var graph = getGraph(input);
            if (graph.reduce() == 3) {
                 return graph.components().stream().map(component -> component.reduced.get()).reduce(1, (a, b) -> a * b);
            }
        }
    }

    @SuppressWarnings("SameReturnValue")
    public static String part2() {
        return "Merry Christmas!";
    }

    static Graph getGraph(List<String> input) {
        var graph = new Graph();
        for (String line : input) {
            String[] parts = line.split(": ");
            var component = graph.getComponent(parts[0]);
            String[] connected = parts[1].split(" ");
            for (var connectedName : connected) {
                component.addConnexion(graph.getComponent(connectedName));
            }
        }
        return graph;
    }

    record Graph(List<Component> components, Map<String, Component> compMap) {

        public Graph() {
            this(new ArrayList<>(), new HashMap<>());
        }

        public Component getComponent(String name) {
            var component = compMap.get(name);
            if (component == null) {
                component = compMap.computeIfAbsent(name, Component::new);
                components.add(component);
            }
            return component;
        }

        public int reduce() {
            while (components.size() > 2) {
                var node1 = components.get(new Random().nextInt(components.size()));
                var node2 = node1.connexions.get(new Random().nextInt(node1.connexions.size()));
                node1.connexions.addAll(node2.connexions);
                for (var node : node2.connexions) {
                    node.connexions.remove(node2);
                    node.connexions.add(node1);
                }
                node1.reduced.addAndGet(node2.reduced.get());
                components.remove(node2);
                node1.connexions.removeIf(n -> n == node1);
            }
            return components.get(0).connexions.size();
        }
    }

    record Component(String name, List<Component> connexions, AtomicInteger reduced) {

        public Component(String name) {
            this(name, new ArrayList<>(), new AtomicInteger(1));
        }

        public void addConnexion(Component other) {
            if (!connexions.contains(other)) {
                connexions.add(other);
                other.addConnexion(this);
            }
        }
    }
}
