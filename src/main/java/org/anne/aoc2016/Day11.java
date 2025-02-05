package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class Day11 extends Day {
    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    public void execute() {
        setName("Radioisotope Thermoelectric Generators");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    static final Pattern PATTERN = Pattern.compile("(\\w+)(?:-compatible)? (microchip|generator)");

    public static int part1(List<String> input) {
        return bfs(parseInput(input));
    }

    public static int part2(List<String> input) {
        State initial = parseInput(input);
        initial.floors.getFirst().addAll(Arrays.asList(
                new Item("elerium", true),
                new Item("elerium", false),
                new Item("dilithium", true),
                new Item("dilithium", false)
        ));
        return bfs(initial);
    }

    private static State parseInput(List<String> input) {
        List<List<Item>> floors = input.stream()
                .map(line -> {
                    List<Item> items = new ArrayList<>();
                    Matcher m = PATTERN.matcher(line);
                    while (m.find()) {
                        items.add(new Item(m.group(1), m.group(2).equals("generator")));
                    }
                    return items;
                })
                .collect(Collectors.toList());
        return new State(floors, 0);
    }

    static Set<List<Item>> getSubsets(List<Item> items, int n) {
        if (n == 0 || n > items.size()) {
            return Set.of(emptyList());
        }
        Set<List<Item>> result = new HashSet<>();
        for (int i = 0; i <= items.size() - n; i++) {
            Set<List<Item>> subsets = getSubsets(items.subList(i + 1, items.size()), n - 1);
            for (List<Item> subset : subsets) {
                List<Item> newSubset = new ArrayList<>(subset);
                newSubset.add(items.get(i));
                if (newSubset.size() == 2 && !newSubset.getFirst().isCompatible(newSubset.get(1))) {
                    continue;
                }
                result.add(newSubset);
            }
        }
        return result;
    }

    static List<State> listSteps(State state) {
        List<State> results = new ArrayList<>();

        for (int n : List.of(1, 2)) {
            for (List<Item> items : getSubsets(state.floors.get(state.elevator), n)) {
                if (items.isEmpty()) {
                    continue;
                }

                for (int newElevator : List.of(state.elevator - 1, state.elevator + 1)) {
                    if (newElevator < 0 || newElevator >= 4 ||
                            (newElevator < state.elevator && state.floors.subList(0, state.elevator).stream().allMatch(List::isEmpty))) {
                        continue;
                    }

                    List<List<Item>> newFloors = state.floors.stream()
                            .map(ArrayList::new)
                            .collect(Collectors.toList());

                    for (Item item : items) {
                        newFloors.get(state.elevator).remove(item);
                        newFloors.get(newElevator).add(item);
                    }
                    State newState = new State(newFloors, newElevator);
                    if (newState.isValid()) results.add(newState);
                }
            }
        }
        return results;
    }

    static int bfs(State initial) {
        Deque<State> queue = new ArrayDeque<>();
        queue.add(initial);

        Map<String, State> parents = new HashMap<>();
        parents.put(initial.toString(), null);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.isEndState()) {
                return getPathLength(current, parents);
            }

            for (State neighbor : listSteps(current)) {
                String key = neighbor.toString();
                if (parents.containsKey(key)) {
                    continue;
                }
                parents.put(key, current);
                queue.add(neighbor);
            }
        }

        return -1;
    }

    static int getPathLength(State end, Map<String, State> parents) {
        List<State> path = new ArrayList<>();
        String key = end.toString();
        while (true) {
            State parent = parents.get(key);
            if (parent == null) {
                return path.size();
            }
            path.add(parent);
            key = parent.toString();
        }
    }

    record State(List<List<Item>> floors, int elevator) {
        boolean isValid() {
            for (List<Item> floor : floors) {
                Set<String> generators = floor.stream().filter(i -> i.isGenerator).map(i -> i.element).collect(Collectors.toSet());
                if (!generators.isEmpty()) {
                    for (Item item : floor) {
                        if (!item.isGenerator && !generators.contains(item.element)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return floors.stream()
                    .flatMap(floor -> floor.stream().map(item -> Map.entry(floors.indexOf(floor), item)))
                    .collect(Collectors.groupingBy(entry -> entry.getValue().element()))
                    .values().stream()
                    .map(entries -> new int[]{
                            entries.stream().filter(e -> !e.getValue().isGenerator).mapToInt(Map.Entry::getKey).findFirst().orElseThrow(),
                            entries.stream().filter(e -> e.getValue().isGenerator).mapToInt(Map.Entry::getKey).findFirst().orElseThrow()
                    })
                    .sorted(Comparator.comparing(Arrays::toString))
                    .map(Arrays::toString)
                    .collect(Collectors.joining(",")) + "," + elevator;
        }

        boolean isEndState() {
            return floors.subList(0, floors.size() - 1)
                    .stream()
                    .allMatch(List::isEmpty);
        }
    }

    record Item(String element, Boolean isGenerator) {
        boolean isCompatible(Item other) {
            return element.equals(other.element) || isGenerator == other.isGenerator;
        }
    }
}
