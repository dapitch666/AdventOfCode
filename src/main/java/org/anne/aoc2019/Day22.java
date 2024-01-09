package org.anne.aoc2019;

import org.anne.common.Day;

import java.math.BigInteger;
import java.util.List;

public class Day22 extends Day {

    public static void main(String[] args) {
        Day day = new Day22();
        day.setName("Slam Shuffle");
        List<String> input = day.readFile();
        day.setPart1(part1(input, 10007, 2019));
        day.setPart2(part2(input, 119315717514047L, 101741582076661L, 2020));
        day.printParts();
    }

    public static BigInteger part1(List<String> input, int deckSize, int position) {
        return shuffle(input, BigInteger.valueOf(deckSize),
                BigInteger.valueOf(deckSize - 2), BigInteger.valueOf(position));
    }

    public static BigInteger part2(List<String> input, long deckSize, long shuffleTimes, int position) {
        return shuffle(input, BigInteger.valueOf(deckSize),
                BigInteger.valueOf(shuffleTimes), BigInteger.valueOf(position));
    }
    
    static BigInteger shuffle(List<String> input, BigInteger deckSize, BigInteger shuffleTimes, BigInteger position) {
        Buffer buffer = new Buffer();
        for (String line : input) {
            if (line.startsWith("deal into new stack")) {
                buffer = buffer.dealIntoNewStack();
            } else if (line.startsWith("cut")) {
                buffer = buffer.cutCards(Integer.parseInt(line.substring(4)));
            } else if (line.startsWith("deal with increment")) {
                buffer = buffer.dealWithIncrement(Integer.parseInt(line.substring(20)), deckSize);
            }
        }
        return buffer.getCardAtPosition(deckSize, shuffleTimes, position);
    }
    
    record Buffer(BigInteger increment, BigInteger offset) {
        Buffer () {
            this(BigInteger.ONE, BigInteger.ZERO);
        }
        
        Buffer dealIntoNewStack() {
            return new Buffer(increment.negate(), offset.subtract(increment));
        }
        
        Buffer cutCards(int n) {
            return new Buffer(increment, offset.add(increment.multiply(BigInteger.valueOf(n))));
        }
        
        Buffer dealWithIncrement(int n, BigInteger deckSize) {
            return new Buffer(
                    increment.multiply(BigInteger.valueOf(n).modPow(deckSize.subtract(BigInteger.TWO), deckSize)),
                    offset);
        }
        
        BigInteger getCardAtPosition(BigInteger deckSize, BigInteger shuffleTimes, BigInteger position) {
            var finalIncrement = increment.modPow(shuffleTimes, deckSize);
            var finalOffset = offset.multiply(finalIncrement.subtract(BigInteger.ONE))
                                           .multiply(increment.subtract(BigInteger.ONE).modInverse(deckSize));
            return finalIncrement.multiply(position).add(finalOffset).mod(deckSize);
        }
    }
}
