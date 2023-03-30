package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;

public class Day16 extends Day {
    private static final List<Interval> intervals = new ArrayList<>();
    private static final Set<int[]> validTickets = new HashSet<>();
    private static final List<Field> fields = new ArrayList<>();
    private static int[] myTicket;

    public static void main(String[] args) {
        Day day = new Day16();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2());
        day.printParts();
    }

    public static long part1(List<String> input) {
        List<String> rules = new ArrayList<>();
        List<int[]> tickets = new ArrayList<>();

        int firstEmptyLineIdx = input.indexOf("");
        int lastEmptyLineIdx = input.lastIndexOf("");
        for (int i = 0; i < firstEmptyLineIdx; i++) {
            rules.add(input.get(i));
        }
        myTicket = parseTicket(input.get(firstEmptyLineIdx + 2));
        for (int i = lastEmptyLineIdx + 2; i < input.size(); i++) {
            tickets.add(parseTicket(input.get(i)));
        }
        for (String line : rules) {
            Field field = new Field(line);
            fields.add(field);
            intervals.addAll(field.getIntervals());
        }
        int count = 0;
        for (int[] ticket : tickets) {
            boolean valid = true;
            for (int i : ticket) {
                if (isInvalid(i)) {
                    count += i;
                    valid = false;
                }
            }
            if (valid) {
                validTickets.add(ticket);
            }
        }
        return count;
    }

    public static long part2() {
        validTickets.add(myTicket);
        Field[] orderedFields = new Field[myTicket.length];
        for (int i = 0; i < myTicket.length; i++) {
            List<Integer> integerList = new ArrayList<>();
            for (int[] t : validTickets) {
                integerList.add(t[i]);
            }
            for (Field field : fields) {
                if (field.areValid(integerList)) {
                    field.addOrder(i);
                }
            }
        }

        for (int i = 0; i < orderedFields.length; i++) {
            for (Field field : fields) {
                if (field.countOrders() == 1) {
                    int currentOrder = field.getOrder();
                    orderedFields[currentOrder] = field;
                    for (Field f : fields) {
                        f.removeOrder(currentOrder);
                    }
                    break;
                }
            }
        }
        long myTicketDeparture = 1;
        int i = 0;
        for (Field field : orderedFields) {
            if (field.startsWithDeparture()) {
                myTicketDeparture *= myTicket[i];
            }
            i++;
        }
        return myTicketDeparture;
    }

    private static boolean isInvalid(int i) {
        for (Interval interval : intervals) {
            if (interval.validate(i)) {
                return false;
            }
        }
        return true;
    }

    private static int[] parseTicket(String line) {
        return Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    static class Field {
        private final String name;
        private final Interval interval1;
        private final Interval interval2;
        private final Set<Integer> orders = new HashSet<>();

        public Field(String s) {
            String[] sSplit = s.split(": ");
            this.name = sSplit[0];
            this.interval1 = new Interval(sSplit[1].split(" or ")[0]);
            this.interval2 = new Interval(sSplit[1].split(" or ")[1]);
        }

        public boolean startsWithDeparture() {
            return name.matches("departure(.*)");
        }

        public List<Interval> getIntervals() {
            return Arrays.asList(interval1, interval2);
        }

        public boolean areValid(List<Integer> ints) {
            for (int j : ints) {
                if (!interval1.validate(j) && !interval2.validate(j)) {
                    return false;
                }
            }
            return true;
        }

        public void addOrder(int order) {
            this.orders.add(order);
        }

        public void removeOrder(int order) {
            this.orders.remove(order);
        }

        public int countOrders() {
            return this.orders.size();
        }

        public int getOrder() {
            return this.orders.iterator().next();
        }
    }

    private record Interval (int min, int max) {
        public Interval(String s) {
            this(Integer.parseInt(s.split("-")[0]), Integer.parseInt(s.split("-")[1]));
        }

        boolean validate(int i) {
            return i >= min && i <= max;
        }
    }
}
