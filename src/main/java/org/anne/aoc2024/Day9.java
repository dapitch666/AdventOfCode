package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day9 extends Day {
    public static void main(String[] args) {
        Day day = new Day9();
        day.setName("Disk Fragmenter");
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(String in) {
        var input = in.chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList();
        var freeSpace = IntStream.range(0, input.size())
                .filter(i -> i % 2 == 1)
                .map(input::get)
                .toArray();
        int totalFreeSpace = Arrays.stream(freeSpace).sum();
        var usedSpace = IntStream.range(0, input.size())
                .filter(i -> i % 2 == 0)
                .map(input::get)
                .toArray();
        int totalUsedSpace = Arrays.stream(usedSpace).sum();
        var files = new int[totalUsedSpace];
        var disk = new int[totalUsedSpace + totalFreeSpace];
        int currentIndex = 0;
        for (int i = 0; i < input.size(); i++) {
            int size = input.get(i);
            if (i % 2 == 0) {
                Arrays.fill(disk, currentIndex, currentIndex + size, i / 2);
            } else {
                Arrays.fill(disk, currentIndex, currentIndex + size, -1);
            }
            currentIndex += size;
        }
        int currentFileIndex = disk.length - 1;
        for (int i = 0; i < files.length; i++) {
            if (disk[i] == -1) {
                while (disk[currentFileIndex] == -1) {
                    currentFileIndex--;
                }
                files[i] = disk[currentFileIndex];
                currentFileIndex--;
            } else {
                files[i] = disk[i];
            }
        }

        return getChecksum(files);
    }

    public static long part2(String in) {
        var disk = new ArrayList<block>();
        for (int i = 0; i < in.length(); i++) {
            int size = Character.getNumericValue(in.charAt(i));
            if (i % 2 == 0) {
                disk.add(new block(size, i/2));
            } else {
                disk.add(new block(size, -1));
            }
        }
        for (int i = disk.size() - 1; i > 0; i--) {
            block currentBlock = disk.get(i);
            if (currentBlock.isFile()) {
                int currentFileSize = currentBlock.size;
                for (int j = 1; j < i; j++) {
                    if (!disk.get(j).isFile()) {
                        int freeSpaceSize = disk.get(j).size;
                        if (freeSpaceSize >= currentFileSize) {
                            int remainingFreeSpace = freeSpaceSize - currentFileSize;
                            // Free the space previously occupied by currentBlock
                            disk.set(i, new block(currentFileSize, -1));
                            // Concatenate free space
                            concatenateFreeSpace(i, disk);
                            // Move currentBlock to free space
                            disk.set(j, new block(currentFileSize, currentBlock.value));
                            if (remainingFreeSpace > 0) {
                                disk.add(j + 1, new block(remainingFreeSpace, -1));
                            }
                            break;
                        }
                    }
                }
            }
        }
        /*var input = in.chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList();
        var freeSpace = IntStream.range(0, input.size())
                .filter(i -> i % 2 == 1)
                .map(input::get)
                .toArray();
        int totalFreeSpace = Arrays.stream(freeSpace).sum();
        var usedSpace = IntStream.range(0, input.size())
                .filter(i -> i % 2 == 0)
                .map(input::get)
                .toArray();
        int totalUsedSpace = Arrays.stream(usedSpace).sum();
        var disk = new int[totalUsedSpace + totalFreeSpace];
        int currentIndex = 0;
        for (int i = 0; i < input.size(); i++) {
            int size = input.get(i);
            if (i % 2 == 0) {
                Arrays.fill(disk, currentIndex, currentIndex + size, i / 2);
            } else {
                Arrays.fill(disk, currentIndex, currentIndex + size, -1);
            }
            currentIndex += size;
        }

        for (int i = input.size() - 1 ; i > 0 ; i -= 2) { // get all files size backwards
            int currentFileSize = input.get(i);
            // check if we can find a free space that fits the file
            boolean placeFound = false;
            for (int j = 0; j < freeSpace.length; j ++) {
                if (placeFound) {
                    break;
                }
                int freeSpaceSize = freeSpace[j];
                if (freeSpaceSize >= currentFileSize) {
                    placeFound = true;
                    // Remove the file from the disk
                    for (int k = 0; k < disk.length; k++) {
                        if (disk[k] == i / 2) {
                            disk[k] = -1;
                        }
                    }
                    currentIndex = IntStream.range(0, j * 2) // PAS BON!
                            .map(input::get)
                            .sum();
                    for (int k = 0; k < currentFileSize; k++) {
                        disk[currentIndex + k] = i / 2;
                    }
                    freeSpace[j] = freeSpaceSize - currentFileSize;
                }
            }
        }*/

        /*
        while (canMove) {
            for (int i = 1; i < input.size(); i += 2) {
                if (input.get(input.size() - i) < input.get(i)) {
                    for (int j = input.size(); j < input.get(i); j++) {
                        disk[currentIndex] = input.get(j) / 2;
                        currentIndex++;
                    }
                }
            }
        }*/
        /*
        for (int i = input.size() - 1; i > 0; i -= 2) {
            int currentFileSize = input.get(i);
            for (int j = 1; j < input.size(); j += 2) {
                int freeSpaceSize = input.get(j);
                if (freeSpaceSize >= currentFileSize) {
                    for (int k = 0; k < currentFileSize; k++) {
                        disk[currentIndex] = i / 2;
                        currentIndex++;
                    }
                    break;
                } else {
                    for (int k = 0; k < currentFileSize; k++) {
                        disk[] = -1;
                        currentIndex++;
                    }
                }
            }
            if (input.get(i) < input.get(input.size() - i)) {
                for (int j = 0; j < input.get(i); j++) {
                    disk[currentIndex] = input.get(j) / 2;
                    currentIndex++;
                }
            }
        }

         */
        return getChecksum(disk);
    }

    private static void concatenateFreeSpace(int index, ArrayList<block> disk) {
        int size = disk.get(index).size;
        List<Integer> indexToRemove = new ArrayList<>();
        if (index < disk.size() - 1 && disk.get(index + 1).value == -1) {
            size += disk.get(index + 1).size;
            indexToRemove.add(index + 1);
        }
        if (index > 0 && disk.get(index - 1).value == -1) {
            size += disk.get(index - 1).size;
            indexToRemove.add(index - 1);
        }
        disk.set(index, new block(size, disk.get(index).value));
        for (int i : indexToRemove) {
            disk.remove(i);
        }
    }

    static long getChecksum(int[] files) {
        return IntStream.range(0, files.length)
                .mapToLong(i -> (long) i * files[i])
                .sum();
    }

    static long getChecksum(block[] files) {
        long result = 0L;
        int index = 0;
        for (block file : files) {
            if (file.isFile()) {
                for (int j = 0; j < file.size; j++) {
                    result += (long) index * file.value;
                    index++;
                }
            } else {
                index += file.size;
            }
        }
        return result;
    }

    static long getChecksum(ArrayList<block> files) {
        long result = 0L;
        int index = 0;
        for (block file : files) {
            if (file.isFile()) {
                for (int j = 0; j < file.size; j++) {
                    result += (long) index * file.value;
                    index++;
                }
            } else {
                index += file.size;
            }
        }
        return result;
    }

    record block(int size, int value) {
        boolean isFile() {
            return value != -1;
        }
    }
}
