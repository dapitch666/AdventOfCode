package org.anne.aoc2021;

import org.anne.common.Day;
import org.anne.common.Point3d;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day19 extends Day {
    static List<Scanner> scanners;

    public static void main(String[] args) {
        Day day = new Day19();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2());
        day.printParts();
    }

    public static int part1(List<String> input) {
        scanners = getScanners(input);
        Set<Point3d> trenchMap = new HashSet<>();
        List<Scanner> done = new ArrayList<>();
        done.add(scanners.get(0));
        int i = 0;
        while (done.size() < scanners.size()) {
            Scanner current = done.get(i);
            for (Scanner scanner : scanners) {
                if (done.contains(scanner)) {
                    continue;
                }
                Rotation rotation = current.getRotationIfExists(scanner);
                if (rotation != null) {
                    scanner.rotationList.addAll(current.rotationList);
                    scanner.rotationList.add(rotation);
                    done.add(scanner);
                }
            }
            i++;
        }
        done.stream().map(Scanner::getRotationTo0).forEach(trenchMap::addAll);
        return trenchMap.size();
    }

    public static int part2() {
        List<Point3d> placements = scanners.stream().map(Scanner::getPlacementTo0).toList();
        int maxDistance = 0;
        for (int i = 0; i < placements.size(); i++) {
            for (Point3d placement : placements) {
                int distance = placement.manhattanDistance(placements.get(i));
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }
        }
        return maxDistance;
    }

    static List<Scanner> getScanners(List<String> input) {
        List<List<Point3d>> inputList = new ArrayList<>();
        int i = -1;
        for (String line : input) {
            if (!line.isEmpty()) {
                if (line.charAt(1) == '-') {
                    inputList.add(new ArrayList<>());
                    i++;
                } else {
                    String[] coordinates = line.split(",");
                    inputList.get(i).add(new Point3d(Integer.parseInt(coordinates[0]),
                            Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2])));
                }
            }
        }
        return inputList.stream().map(Scanner::new).collect(Collectors.toList());
    }

    public static class Scanner {

        final List<Point3d> givenMeasurements;
        final List<Measurement> measurements;

        final List<Rotation> rotationList = new ArrayList<>();

        public Scanner(List<Point3d> givenMeasurements) {
            this.givenMeasurements = givenMeasurements;
            measurements = givenMeasurements.stream()
                    .map(mes -> new Measurement(mes, givenMeasurements))
                    .collect(Collectors.toList());
        }

        public Point3d getPlacementTo0() {
            Point3d rotated = new Point3d(0, 0, 0);
            for (int i = rotationList.size() - 1; i >= 0; i--) {
                rotated = rotationList.get(i).apply(rotated);
            }
            return rotated;
        }

        List<Point3d> getRotationTo0() {
            return givenMeasurements.stream().map(gm -> {
                Point3d rotated = gm;
                for (int i = rotationList.size() - 1; i >= 0; i--) {
                    rotated = rotationList.get(i).apply(rotated);
                }
                return rotated;
            }).collect(Collectors.toList());
        }

        public Rotation getRotationIfExists(Scanner scanner) {
            DistPair commons = null;
            for (Measurement m : measurements) {
                if (commons != null) {
                    break;
                }
                for (Measurement s : scanner.measurements) {
                    if (commons != null) {
                        break;
                    }
                    commons = m.has12Common(s);
                }
            }
            if (commons == null) {
                return null;
            }
            return calculateRotation(commons);
        }

        private Rotation calculateRotation(DistPair commons) {
            for (int i = 1; i < 25; i++) {
                Rotation rotation = Rotation.of(i);
                Point3d move = rotation.matches(commons);
                if (move != null) {
                    rotation.setMove(move);
                    return rotation;
                }
            }
            return null;
        }

    }

    public static class Rotation {
        final Function<Point3d, Integer> getX;
        final Function<Point3d, Integer> getY;
        final Function<Point3d, Integer> getZ;
        Point3d move;

        public Rotation(Function<Point3d, Integer> getX,
                        Function<Point3d, Integer> getY,
                        Function<Point3d, Integer> getZ) {
            this.getX = getX;
            this.getY = getY;
            this.getZ = getZ;
        }

        public void setMove(Point3d move) {
            this.move = move;
        }

        public Point3d apply(Point3d to) {
            Point3d rotated = new Point3d(getX.apply(to), getY.apply(to), getZ.apply(to));
            return move == null ? rotated : rotated.add(move);
        }

        @SuppressWarnings("Convert2MethodRef")
        public static Rotation of(int num) {
            return switch (num) {
                case  1 -> new Rotation(c ->  c.x(), c ->  c.y(), c ->  c.z());
                case  2 -> new Rotation(c ->  c.x(), c ->  c.z(), c -> -c.y());
                case  3 -> new Rotation(c ->  c.x(), c -> -c.y(), c -> -c.z());
                case  4 -> new Rotation(c ->  c.x(), c -> -c.z(), c ->  c.y());
                case  5 -> new Rotation(c -> -c.x(), c -> -c.y(), c ->  c.z());
                case  6 -> new Rotation(c -> -c.x(), c ->  c.z(), c ->  c.y());
                case  7 -> new Rotation(c -> -c.x(), c ->  c.y(), c -> -c.z());
                case  8 -> new Rotation(c -> -c.x(), c -> -c.z(), c -> -c.y());
                case  9 -> new Rotation(c ->  c.y(), c -> -c.x(), c ->  c.z());
                case 10 -> new Rotation(c ->  c.y(), c ->  c.z(), c ->  c.x());
                case 11 -> new Rotation(c ->  c.y(), c ->  c.x(), c -> -c.z());
                case 12 -> new Rotation(c ->  c.y(), c -> -c.z(), c -> -c.x());
                case 13 -> new Rotation(c -> -c.y(), c ->  c.x(), c ->  c.z());
                case 14 -> new Rotation(c -> -c.y(), c -> -c.z(), c ->  c.x());
                case 15 -> new Rotation(c -> -c.y(), c -> -c.x(), c -> -c.z());
                case 16 -> new Rotation(c -> -c.y(), c ->  c.z(), c -> -c.x());
                case 17 -> new Rotation(c ->  c.z(), c ->  c.y(), c -> -c.x());
                case 18 -> new Rotation(c ->  c.z(), c ->  c.x(), c ->  c.y());
                case 19 -> new Rotation(c ->  c.z(), c -> -c.y(), c ->  c.x());
                case 20 -> new Rotation(c ->  c.z(), c -> -c.x(), c -> -c.y());
                case 21 -> new Rotation(c -> -c.z(), c ->  c.y(), c ->  c.x());
                case 22 -> new Rotation(c -> -c.z(), c -> -c.x(), c ->  c.y());
                case 23 -> new Rotation(c -> -c.z(), c -> -c.y(), c -> -c.x());
                case 24 -> new Rotation(c -> -c.z(), c ->  c.x(), c -> -c.y());
                default -> null;
            };
        }

        public Point3d matches(DistPair commons) {
            Point3d newOrigin = this.apply(commons.right.get(0).from);
            List<BeaconDistance> transformed = commons.right.stream()
                    .map(bd -> new BeaconDistance(newOrigin, this.apply(bd.to), bd.distance))
                    .toList();
            int errorLimit = transformed.size() - 11;
            Map<Integer, Long> grouped = commons.left.stream().map(BeaconDistance::getDistance)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (Long val : grouped.values()) {
                if (val > 1) {
                    errorLimit += val * (val - 1);
                }
            }
            Point3d dist = commons.left.get(0).from.add(transformed.get(0).from.reverse());
            int errCount = 0;
            for (int i = 0; i < commons.left.size(); i++) {
                BeaconDistance left = commons.left.get(i);
                for (int j = 1; j < transformed.size(); j++) {
                    BeaconDistance right = transformed.get(j);
                    if (left.distance.equals(right.distance)) {
                        Point3d currentDist = left.to.add(right.to.reverse());
                        if (!dist.equals(currentDist)) {
                            errCount++;
                        }
                    }
                    if (errCount > errorLimit) {
                        return null;
                    }
                }
            }
            return dist;
        }
    }

    public static class Measurement {
        final List<BeaconDistance> relative;

        public Measurement(Point3d measurement, List<Point3d> all) {
            relative = new ArrayList<>();
            for (Point3d coordinate : all) {
                if (coordinate != measurement) {
                    relative.add(new BeaconDistance(measurement, coordinate));
                }
            }
        }

        public DistPair has12Common(Measurement with) {
            Set<Integer> withDistances = with.relative.stream()
                    .map(BeaconDistance::getDistance)
                    .collect(Collectors.toSet());
            List<Integer> commonDistances = relative.stream()
                    .map(BeaconDistance::getDistance)
                    .filter(withDistances::contains)
                    .toList();
            if (commonDistances.size() < 11) {
                return null;
            } else {
                List<BeaconDistance> firstList = relative.stream()
                        .filter(bd -> commonDistances.contains(bd.distance))
                        .collect(Collectors.toList());
                List<BeaconDistance> secondList = with.relative.stream()
                        .filter(bd -> commonDistances.contains(bd.distance))
                        .collect(Collectors.toList());
                return new DistPair(firstList, secondList);
            }
        }
    }

    public static class BeaconDistance {

        final Point3d from;
        final Point3d to;
        final Integer distance;

        public BeaconDistance(Point3d from, Point3d to) {
            this.from = from;
            this.to = to;
            distance = from.manhattanDistance(to);
        }

        public BeaconDistance(Point3d from, Point3d to, Integer distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        public Integer getDistance() {
            return distance;
        }
    }

    record DistPair (List<BeaconDistance> left, List<BeaconDistance> right) {}
}
