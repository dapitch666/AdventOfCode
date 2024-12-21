package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Day9 extends Day {
    public static void main(String[] args) {
        Day day = new Day9();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Disk Fragmenter");
        String input = this.readFileOneLine();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(String in) {
        var input = in.chars().map(Character::getNumericValue).boxed().toList();
        var disk = new int[input.stream().mapToInt(Integer::intValue).sum()];
        int currentIndex = 0;
        for (int i = 0; i < input.size(); i++) {
            int size = input.get(i);
            Arrays.fill(disk, currentIndex, currentIndex + size, (i % 2 == 0) ? i / 2 : -1);
            currentIndex += size;
        }

        // The occupied size is the sum of all even indexes (free space is not needed)
        var files = new int[IntStream.range(0, input.size()).filter(i -> i % 2 == 0).map(input::get).sum()];
        int currentFileIndex = disk.length - 1;
        for (int i = 0; i < files.length; i++) {
            if (disk[i] == -1) {
                while (disk[currentFileIndex] == -1) {
                    currentFileIndex--;
                }
                files[i] = disk[currentFileIndex--];
            } else {
                files[i] = disk[i];
            }
        }

        return getChecksum(files);
    }

    public static long part2(String input) {
        var disk = new ArrayList<Block>();

        for (int i = 0; i < input.length(); i += 2) {
            int size = Character.getNumericValue(input.charAt(i));
            int free = (i == input.length() - 1) ? 0 : Character.getNumericValue(input.charAt(i + 1));
            disk.add(new Block(size, i / 2, free));
        }

        for (int i = disk.size() - 1; i > 0; i--) {
            Block currentBlock = disk.get(i);
            for (int j = 0; j < i; j++) {
                Block freeBlock = disk.get(j);
                if (freeBlock.free >= currentBlock.size) {
                    freeBlock.space.addAll(Collections.nCopies(currentBlock.size, currentBlock.value));
                    disk.set(j, new Block(freeBlock.size, freeBlock.value, freeBlock.free - currentBlock.size, freeBlock.space));
                    // Set value to 0 to indicate that the block is free, this way the checksum will be calculated correctly
                    disk.set(i, new Block(currentBlock.size, 0, currentBlock.free, currentBlock.space));
                    break;
                }
            }
        }
        return getChecksum(disk);
    }

    static long getChecksum(int[] files) {
        return IntStream.range(0, files.length)
                .mapToLong(i -> (long) i * files[i])
                .sum();
    }

    static long getChecksum(List<Block> disk) {
        long result = 0L;
        int index = 0;
        for (Block block : disk) {
            for (int i = 0; i < block.size; i++) {
                result += (long) index * block.value;
                index++;
            }
            for (int space : block.space) {
                    result += (long) index * space;
                index++;
            }
            index += block.free;
        }
        return result;
    }

    record Block(int size, int value, int free, List<Integer> space) {
        public Block(int size, int value, int free) {
            this(size, value, free, new ArrayList<>());
        }
    }
}
