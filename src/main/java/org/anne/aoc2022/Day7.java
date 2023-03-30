package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day7 extends Day {
    public static void main(String[] args) {
        Day day = new Day7();
        Directory root = getRoot(day.readFile());
        day.setPart1(part1(root));
        day.setPart2(part2(root));
        day.printParts();
    }

    public static long part1(Directory root) {
        return root.sumOfAllDirectoriesWithTotalSizeAtMost(100000);
    }

    public static long part2(Directory root) {
        long minSize = root.sizeTotal() - (70000000 - 30000000);
        return root.smallestDirectoryWithEnoughSize(minSize);
    }

    public static Directory getRoot(List<String> input) {
        Directory root = new Directory(null);
        Directory current = root;

        for(String line : input.subList(1, input.size())) {
            String[] param = line.split(" ");
            switch (param[0]) {
                case "$" -> {
                    if (param[1].equals("cd")) {
                        String dirName = param[2];
                        if(dirName.equals("..")) {
                            current = current.parent;
                        } else {
                            Directory newDir = new Directory(current);
                            current.children.add(newDir);
                            current = newDir;
                        }
                    }
                }
                case "dir" -> {
                    // Do nothing
                }
                default -> current.filesSize += Integer.parseInt(param[0]);
            }
        }
        return root;
    }

    public static class Directory {
        final Directory parent;
        final ArrayList<Directory> children;
        long filesSize;

        public Directory(Directory parent) {
            this.parent = parent;
            this.children = new ArrayList<>();
            this.filesSize = 0;
        }

        public long sizeTotal() {
            long total = 0;
            for (Directory directory : this.children) {
                total += directory.sizeTotal();
            }
            return total + this.filesSize;
        }

        public long sumOfAllDirectoriesWithTotalSizeAtMost(int maxSize) {
            long total = 0;
            for (Directory directory : this.children) {
                total += directory.sumOfAllDirectoriesWithTotalSizeAtMost(maxSize);
            }
            if (this.sizeTotal() <= maxSize) {
                total += this.sizeTotal();
            }
            return total;
        }

        public long smallestDirectoryWithEnoughSize(long minSize) {
            long size = Long.MAX_VALUE;
            for (Directory directory : this.children) {
                size = Math.min(size, directory.smallestDirectoryWithEnoughSize(minSize));
            }
            if (this.sizeTotal() > minSize) {
                size = Math.min(size, this.sizeTotal());
            }
            return size;
        }
    }
}
