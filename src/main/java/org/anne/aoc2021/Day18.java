package org.anne.aoc2021;


import org.anne.common.Day;

import java.util.*;

public class Day18 extends Day {

    public static void main(String[] args) {
        Day day = new Day18();
        day.setName("Snailfish");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static int part1(List<String>  input) {
        List<Pair> pairs = new ArrayList<>();
        for (String line : input) {
           Pair pair = parseString(line);
           pairs.add(pair);
        }
        Collections.reverse(pairs);
        Stack<Pair> pairStack = new Stack<>();
        pairStack.addAll(pairs);
        Pair result = pairStack.pop();
        while (!pairStack.isEmpty()) {
            result = addition(result, pairStack.pop());
        }
        return result.magnitude();
    }

    public static int part2(List<String>  input) {
        int max = 0;
        for (String first : input) {
            Pair firstPair = parseString(first);
            for (String second : input) {
                Pair secondPair = parseString(second);
                max = Math.max(max, addition(firstPair.copy(), secondPair.copy()).magnitude());
            }
        }
        return max;
    }

    static Pair parseString(String s) {
        if (Character.isDigit(s.charAt(0))) {
            return new Pair(Integer.parseInt(s));
        }
        int level = 0;
        int pos = -1;
        for (int i = 0; i < s.length() && pos == -1; i++) {
            switch (s.charAt(i)) {
                case '[' -> level++;
                case ']' -> level--;
                case ',' -> {
                    if (level == 1) {
                        pos = i;
                    }
                }
            }
        }
        return new Pair(parseString(s.substring(1, pos)), parseString(s.substring(pos + 1, s.length() -1)));
    }

    static Pair addition(Pair firstPair, Pair secondPair) {
        Pair pair = new Pair(firstPair, secondPair);
        pair.reduce();
        return pair;
    }

    static class Pair {
        Pair x;
        Pair y;
        Integer n;
        Pair father;

        Pair(int n) {
            this.n = n;
        }

        public Pair(Pair x, Pair y) {
            this.x = x;
            this.y = y;
            x.father = this;
            y.father = this;
        }

        private int depth() {
            return father == null ? 0 : father.depth() + 1;
        }

        private boolean hasParent() {
            return this.father != null;
        }

        private Pair findAdam() {
            Pair p = this;
            while (p.hasParent()) {
                p = p.father;
            }
            return p;
        }

        private List<Pair> numbers() {
            if (isNumberPair()) {
                return List.of(this);
            }
            List<Pair> numbers = new ArrayList<>();
            numbers.addAll(x.numbers());
            numbers.addAll(y.numbers());
            return numbers;
        }

        public boolean isNumberPair() {
            return this.x == null && this.y == null;
        }

        private boolean explode() {
            if (!isNumberPair()) {
                if (depth() == 4) {
                    findNearNumber(x, true).ifPresent(sn -> sn.n += x.n);
                    findNearNumber(y, false).ifPresent(sn -> sn.n += y.n);
                    this.reset();
                    return true;
                } else {
                    return x.explode() || y.explode();
                }
            }
            return false;
        }

        private Optional<Pair> findNearNumber(Pair p, boolean isLeft) {
            List<Pair> numbers = findAdam().numbers();
            int pos = numbers.indexOf(p);
            if (isLeft) {
                if (pos > 0) {
                    return Optional.of(numbers.get(pos - 1));
                }
            } else {
                if (pos < numbers.size() - 1) {
                    return Optional.of(numbers.get(pos + 1));
                }
            }
            return Optional.empty();
        }

        private boolean split() {
            if (isNumberPair()) {
                if (n > 9) {
                    x = new Pair(n / 2);
                    y = new Pair((int) Math.ceil((double)n / 2));
                    x.father = this;
                    y.father = this;
                    n = null;
                    return true;
                }
            } else {
                return x.split() || y.split();
            }
            return false;
        }

        void reduce() {
            boolean done = true;
            while (done) {
                done = explode() || split();
            }
        }

        private void reset() {
            this.x = null;
            this.y = null;
            this.n = 0;
        }

        int magnitude() {
            if (this.isNumberPair()) {
                return this.n;
            } else {
                return 3 * this.x.magnitude() + 2 * this.y.magnitude();
            }
        }

        Pair copy() {
            if (this.isNumberPair()) {
                return new Pair(this.n);
            } else {
                return new Pair(this.x.copy(), this.y.copy());
            }
        }

        @Override
        public String toString() {
            if (x == null && y == null) {
                return String.valueOf(n);
            } else {
                return String.format("[%s,%s]", x, y);
            }
        }
    }
}
