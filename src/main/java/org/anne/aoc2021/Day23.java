package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day23 extends Day {

    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Amphipod");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String>  input) {
        char[][] rooms = new char[][] {
                {input.get(2).charAt(3), input.get(3).charAt(3)},
                {input.get(2).charAt(5), input.get(3).charAt(5)},
                {input.get(2).charAt(7), input.get(3).charAt(7)},
                {input.get(2).charAt(9), input.get(3).charAt(9)}
        };
        return getMinEnergy(rooms);
    }

    public static int part2(List<String>  input) {
        char[][] rooms = new char[][] {
                {input.get(2).charAt(3), 'D', 'D', input.get(3).charAt(3)},
                {input.get(2).charAt(5), 'C', 'B', input.get(3).charAt(5)},
                {input.get(2).charAt(7), 'B', 'A', input.get(3).charAt(7)},
                {input.get(2).charAt(9), 'A', 'C', input.get(3).charAt(9)}
        };
        return getMinEnergy(rooms);
    }

    static int getMinEnergy(char[][] rooms) {
        Diagram start = new Diagram(rooms);
        Map<Diagram, Integer> solutions = new HashMap<>();
        PriorityQueue<Diagram> queue = new PriorityQueue<>(Comparator.comparingLong(Diagram::getEnergy));
        queue.add(start);
        int minEnergy = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Diagram currentDiagram = queue.poll();
            if (currentDiagram.isComplete()) {
                minEnergy = Math.min(minEnergy, currentDiagram.getEnergy());
            }
            solutions.put(currentDiagram, currentDiagram.getEnergy());
            currentDiagram.next().stream()
                    .filter(diagram -> !solutions.containsKey(diagram) || solutions.get(diagram) > diagram.getEnergy())
                    .forEach(diagram -> {
                        solutions.put(diagram, diagram.getEnergy());
                        queue.add(diagram);
                    });
        }
        return minEnergy;
    }

    public static class Diagram {
        private final char[] hallway = new char[11];
        private final char[][] rooms;
        private int energy;

        public Diagram(char[][] rooms) {
            Arrays.fill(hallway, '.');
            this.rooms = rooms;
            this.energy = 0;
        }

        public Diagram(Diagram other) {
            System.arraycopy(other.hallway, 0, hallway, 0, 11);
            this.rooms = deepCopy(other.rooms);
            this.energy = other.energy;
        }


        public List<Diagram> next() {
            List<Diagram> list = new ArrayList<>();

            if (rooms[0][0] != 'A' || rooms[0][1] != 'A') {
                list.addAll(moveOutOfRoom(Diagram::getRoom0, 2));
            }
            if (rooms[1][0] != 'B' || rooms[1][1] != 'B') {
                list.addAll(moveOutOfRoom(Diagram::getRoom1, 4));
            }
            if (rooms[2][0] != 'C' || rooms[2][1] != 'C') {
                list.addAll(moveOutOfRoom(Diagram::getRoom2, 6));
            }
            if (rooms[3][0] != 'D' || rooms[3][1] != 'D') {
                list.addAll(moveOutOfRoom(Diagram::getRoom3, 8));
            }

            for (int i = 0; i < 11; i++) {
                if (hallway[i] != '.') {
                    int j = i - 1;
                    while (j >= 0) {
                        if (hallway[j] == '.') {
                            moveFromHallwayToRoom(i, j).ifPresent(list::add);
                        } else {
                            break;
                        }
                        j--;
                    }
                    j = i + 1;
                    while (j < 11) {
                        if (hallway[j] == '.') {
                            moveFromHallwayToRoom(i, j).ifPresent(list::add);
                        } else {
                            break;
                        }
                        j++;
                    }
                }
            }
            return list;
        }

        private boolean thereIsSpaceInThisRoom(char[] room, char expected) {
            return room[0] == '.' &&
                    IntStream.range(1, room.length)
                            .allMatch(i -> room[i] == '.' || room[i] == expected);
        }

        private int getIndex(char[] room) {
            for (int i = 1; i < room.length; i++) {
                if (room[i] != '.') {
                    return i - 1;
                }
            }
            return room.length - 1;
        }

        private Optional<Diagram> moveFromHallwayToRoom(int origin, int destination) {
            char amphipod = hallway[origin];
            if (destination == 2 && amphipod == 'A' && thereIsSpaceInThisRoom(rooms[0], 'A')) {
                return Optional.of(moveToRoom(0, amphipod, origin, destination));
            }
            if (destination == 4 && amphipod == 'B' && thereIsSpaceInThisRoom(rooms[1], 'B')) {
                return Optional.of(moveToRoom(1, amphipod, origin, destination));
            }
            if (destination == 6 && amphipod == 'C' && thereIsSpaceInThisRoom(rooms[2], 'C')) {
                return Optional.of(moveToRoom(2, amphipod, origin, destination));
            }
            if (destination == 8 && amphipod == 'D' && thereIsSpaceInThisRoom(rooms[3], 'D')) {
                return Optional.of(moveToRoom(3, amphipod, origin, destination));
            }
            return Optional.empty();
        }

        private Diagram moveToRoom(int roomIndex, char amphipod, int origin, int destination) {
            Diagram diagram = new Diagram(this);
            diagram.hallway[origin] = '.';
            int index = getIndex(rooms[roomIndex]);
            diagram.rooms[roomIndex][index] = amphipod;
            int moves = Math.abs(destination - origin) + 1 + index;
            diagram.energy += energyOf(hallway[origin]) * moves;
            return diagram;
        }

        private List<Diagram> moveOutOfRoom(Function<Diagram, char[]> roomSupplier, int roomHallwayIndex) {
            List<Diagram> diagrams = new ArrayList<>();
            char[] room = roomSupplier.apply(this);
            int index = 0;
            while (index < room.length) {
                if (room[index] != '.') {
                    break;
                }
                index++;
            }
            if (index >= room.length) {
                return List.of();
            }
            int i = roomHallwayIndex;
            while (i >= 0) {
                if (hallway[i] == '.') {
                    if (!Arrays.asList(2,4,6,8).contains(i)) {
                        diagrams.add(getDiagram(roomSupplier, roomHallwayIndex, index, i));
                    }
                } else {
                    break;
                }
                i--;
            }
            i = roomHallwayIndex;
            while (i < 11) {
                if (hallway[i] == '.') {
                    if (!Arrays.asList(2,4,6,8).contains(i)) {
                        diagrams.add(getDiagram(roomSupplier, roomHallwayIndex, index, i));
                    }
                } else {
                    break;
                }
                i++;
            }
            return diagrams;
        }

        private Diagram getDiagram(Function<Diagram, char[]> roomSupplier, int roomHallwayIndex, int index, int i) {
            Diagram diagram = new Diagram(this);
            char amphipod = roomSupplier.apply(diagram)[index];
            diagram.hallway[i] = amphipod;
            roomSupplier.apply(diagram)[index] = '.';
            diagram.energy += energyOf(amphipod) * (index + Math.abs(roomHallwayIndex - i) + 1);
            return diagram;
        }

        public int energyOf(char amphipod) {
            return switch (amphipod) {
                case 'A' -> 1;
                case 'B' -> 10;
                case 'C' -> 100;
                case 'D' -> 1000;
                default -> throw new IllegalStateException("Unexpected value: " + amphipod);
            };
        }

        public char[] getRoom0() {
            return rooms[0];
        }

        public char[] getRoom1() {
            return rooms[1];
        }

        public char[] getRoom2() {
            return rooms[2];
        }

        public char[] getRoom3() {
            return rooms[3];
        }

        public boolean roomIsComplete(char[] room, char expected) {
            return new String(room).chars().filter(c -> c == expected).count() == room.length;
        }

        public boolean isComplete() {
            return  roomIsComplete(rooms[0], 'A') &&
                    roomIsComplete(rooms[1], 'B') &&
                    roomIsComplete(rooms[2], 'C') &&
                    roomIsComplete(rooms[3], 'D');
        }

        public int getEnergy() {
            return energy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Diagram diagram = (Diagram) o;
            return Arrays.equals(hallway, diagram.hallway) && Arrays.deepEquals(rooms, diagram.rooms);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Arrays.hashCode(hallway), Arrays.deepHashCode(rooms));
        }
    }

    public static char[][] deepCopy(char[][] matrix) {
        return java.util.Arrays.stream(matrix).map(char[]::clone).toArray($ -> matrix.clone());
    }
}
