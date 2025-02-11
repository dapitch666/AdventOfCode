package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.MathsUtils;

import java.util.*;
import java.util.regex.Pattern;

public class Day20 extends Day {
    public static void main(String[] args) {
        new Day20().run();
    }

    @Override
    public void execute() {
        setName("Pulse Propagation");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }


    public static int part1(List<String> input) {
        var modules = new HashMap<String, Module>();
        var highPulses = 0;
        var lowPulses = 0;
        for (String line : input) {
            Module module = new Module(line);
            modules.put(module.name, module);
        }
        for (Module module : modules.values()) {
            for (var target : module.targets) {
                if (modules.containsKey(target) && modules.get(target).type == Type.CONJUNCTION) {
                    modules.get(target).pulses.put(module.name, false);
                }
            }
        }
        for (var i = 0; i < 1000; i++) {
            var pulseCount = pressButton(modules, new HashMap<>(), i);
            highPulses += pulseCount[0];
            lowPulses += pulseCount[1];
        }
        return highPulses * lowPulses;
    }

    public static long part2(List<String> input) {
        var modules = new HashMap<String, Module>();
        var rxModules = new HashMap<String, Integer>();
        for (String line : input) {
            Module module = new Module(line);
            modules.put(module.name, module);
        }
        for (Module module : modules.values()) {
            for (var target : module.targets) {
                if (modules.containsKey(target) && modules.get(target).type == Type.CONJUNCTION) {
                    modules.get(target).pulses.put(module.name, false);
                } else if (target.equals("rx")) {
                    modules.values().stream().filter(m -> m.targets.contains(module.name)).forEach(m -> rxModules.put(m.name, 0));
                }
            }
        }
        var i = 1;
        while (true) {
            var result = pressButton(modules, rxModules, i)[2];
            if (result > 0) {
                return rxModules.values().stream()
                        .mapToLong(Long::valueOf)
                        .reduce(1, MathsUtils::lcm);
            } else if (rxModules.isEmpty()) {
                return 0;
            }
            i++;
        }
    }

    private static int[] pressButton(Map<String, Module> modules, Map<String, Integer> rxModules, int i) {
        var highPulses = 0;
        var lowPulses = 0;
        var queue = new LinkedList<Pulse>();
        queue.add(new Pulse("button", "broadcaster", false));
        while (!queue.isEmpty()) {
            var pulse = queue.pop();
            if (pulse.isHigh) {
                highPulses++;
                if (rxModules.containsKey(pulse.from)) {
                    rxModules.put(pulse.from, i);
                    if (rxModules.values().stream().allMatch(value -> value > 0)) {
                        return new int[]{0, 0, 1};
                    }
                }
            } else {
                lowPulses++;
            }
            if (!modules.containsKey(pulse.to)) {
                continue;
            }
            var module = modules.get(pulse.to);
            if (module.type == Type.BROADCASTER) {
                module.targets.forEach(t -> queue.add(new Pulse(pulse.to, t, pulse.isHigh)));
            } else if (module.type == Type.CONJUNCTION) {
                module.pulses.put(pulse.from, pulse.isHigh);
                if (module.pulses.values().stream().allMatch(b -> b)) {
                    module.targets.forEach(t -> queue.add(new Pulse(pulse.to, t, false)));
                } else {
                    module.targets.forEach(t -> queue.add(new Pulse(pulse.to, t, true)));
                }
            } else if (module.type == Type.FLIPFLOP && !pulse.isHigh) {
                module.toggle();
                module.targets.forEach(t -> queue.add(new Pulse(pulse.to, t, module.isOn)));
            }
        }
        return new int[]{highPulses, lowPulses, 0};
    }
    
    enum Type {
        FLIPFLOP, CONJUNCTION, BROADCASTER
    }
    
    record Pulse(String from, String to, boolean isHigh) {
        @Override
        public String toString() {
            return from + " -" + (isHigh ? "high" : "low") + "-> " + to;
        }
    }
   
    static class Module {
        String name;
        final List<String> targets = new ArrayList<>();
        final Map<String, Boolean> pulses = new HashMap<>();
        boolean isOn = false;
        Type type;
        
        public Module(String line) {
            var pattern = Pattern.compile("^[%&]?(\\w+) -> (.+)$");
            var matcher = pattern.matcher(line);
            if (matcher.matches()) {
                name = matcher.group(1);
                type = switch (line.charAt(0)) {
                    case '%' -> Type.FLIPFLOP;
                    case '&' -> Type.CONJUNCTION;
                    default -> Type.BROADCASTER;
                };
                Collections.addAll(targets, matcher.group(2).split(", "));
            }
        }
        
        @Override
        public String toString() {
            return "Module{" +
                    "name='" + name + '\'' +
                    ", targets=" + targets +
                    ", isOn=" + isOn +
                    ", type=" + type +
                    '}';
        }
        
        public void toggle() {
            isOn = !isOn;
        }
    }
}
