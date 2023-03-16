package org.anne.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Computer {

    private final long[] intCode;
    private final List<Long> input = new ArrayList<>();
    private final List<Long> output = new ArrayList<>();
    private boolean stopped = false;
    private int currentInstruction = 0;
    private int relativeBase = 0;

    public Computer(String memory) {
        this.intCode = stringToIntCode(memory);
    }

    public Computer(String memory, int memorySize) {
        this.intCode = stringToIntCode(memory, memorySize);
    }

    public long[] getIntCode() {
        return intCode;
    }

    public List<Long> getOutputs() {
        return output;
    }

    public long getOutput() {
        if (this.output.isEmpty()) {
            return 0;
        } else {
            return this.output.remove(0);
        }
    }

    public long getLastOutput() {
        if (this.output.isEmpty()) {
            return 0;
        } else {
            return this.output.get(this.output.size() - 1);
        }
    }

    public void compute(long input) {
        this.input.add(input);
        compute();
    }

    public long computeAndGetOutput(long input) {
        this.input.add(input);
        compute();
        return getOutput();
    }

    public long computeAndGetOutput(long[] input) {
        this.writeInput(input);
        compute();
        return getOutput();
    }

    public void writeInput(long[] input) {
        for (long i : input) {
            this.input.add(i);
        }
    }

    public void writeInput(int input) {
        this.input.add((long) input);
    }

    private long readInput() {
        long firstInput = this.input.get(0);
        this.input.remove(0);
        return firstInput;
    }

    public boolean isStillRunning() {
        return !this.stopped;
    }

    public void compute(int noun, int verb) {
        this.intCode[1] = noun;
        this.intCode[2] = verb;
        compute();
    }

    public void compute() {
        boolean pause = false;
        while (currentInstruction < intCode.length && !pause) {
            long instruction = intCode[currentInstruction];
            int opcode = (int) ((instruction/10)%10 * 10 + instruction%10);
            int mode1 = (int) ((instruction/100)%10);
            int mode2 = (int) ((instruction/1000)%10);
            int mode3 = (int) ((instruction/10000)%10);
            long first, second;
            int address;
            if (opcode == 99) {
                this.stopped = true;
                break;
            }
            switch (opcode) {
                case 1 -> {     // addition
                    first = getParameters(mode1, currentInstruction + 1);
                    second = getParameters(mode2, currentInstruction + 2);
                    address = getAddress(mode3, currentInstruction + 3);
                    intCode[address] = first + second;
                    currentInstruction += 4;
                    journal(instruction, "ADDITION", new ArrayList<>(Arrays.asList(String.valueOf(first), String.valueOf(second), String.valueOf(address))));
                }
                case 2 -> {     // multiplication
                    first = getParameters(mode1, currentInstruction + 1);
                    second = getParameters(mode2, currentInstruction + 2);
                    address = getAddress(mode3, currentInstruction + 3);
                    intCode[address] = first * second;
                    currentInstruction += 4;
                    journal(instruction, "MULTIPLICATION", new ArrayList<>(Arrays.asList(String.valueOf(first), String.valueOf(second), String.valueOf(address))));
                }
                case 3 -> {     // input
                    address = getAddress(mode1, currentInstruction + 1);
                    if (input.isEmpty()) {
                        pause = true;
                    } else {
                        intCode[address] = readInput();
                        currentInstruction += 2;
                    }
                    journal(instruction, "INPUT", new ArrayList<>(Collections.emptyList()));
                }
                case 4 -> {     // output
                    first = getParameters(mode1, currentInstruction + 1);
                    output.add(first);
                    currentInstruction += 2;
                    journal(instruction, "OUTPUT", new ArrayList<>(Collections.singletonList(String.valueOf(first))));
                }
                case 5 -> {     // jump-if-true
                    first = getParameters(mode1, currentInstruction + 1);
                    second = getParameters(mode2, currentInstruction + 2);
                    if (first > 0) {
                        currentInstruction = (int) second;
                    } else {
                        currentInstruction += 3;
                    }
                    journal(instruction, "JUMP-TRUE", new ArrayList<>(Arrays.asList(String.valueOf(first), String.valueOf(second))));
                }
                case 6 -> {     // jump-if-false
                    first = getParameters(mode1, currentInstruction + 1);
                    second = getParameters(mode2, currentInstruction + 2);
                    if (first == 0) {
                        currentInstruction = (int) second;
                    } else {
                        currentInstruction += 3;
                    }
                    journal(instruction, "JUMP-FALSE", new ArrayList<>(Arrays.asList(String.valueOf(first), String.valueOf(second))));
                }
                case 7 -> {     // less than
                    first = getParameters(mode1, currentInstruction + 1);
                    second = getParameters(mode2, currentInstruction + 2);
                    address = getAddress(mode3, currentInstruction + 3);
                    if (first < second) {
                        intCode[address] = 1;
                    } else {
                        intCode[address] = 0;
                    }
                    currentInstruction += 4;
                    journal(instruction, "LESS", new ArrayList<>(Arrays.asList(String.valueOf(first), String.valueOf(second), String.valueOf(address))));
                }
                case 8 -> {     // equals
                    first = getParameters(mode1, currentInstruction + 1);
                    second = getParameters(mode2, currentInstruction + 2);
                    address = getAddress(mode3, currentInstruction + 3);
                    if (first == second) {
                        intCode[address] = 1;
                    } else {
                        intCode[address] = 0;
                    }
                    currentInstruction += 4;
                    journal(instruction, "EQUALS", new ArrayList<>(Arrays.asList(String.valueOf(first), String.valueOf(second), String.valueOf(address))));
                }
                case 9 -> {     // Relative base offset
                    first = getParameters(mode1, currentInstruction + 1);
                    this.relativeBase += first;
                    currentInstruction += 2;
                    journal(instruction, "OFFSET", new ArrayList<>(Collections.singletonList(String.valueOf(first))));
                }
            }
        }
    }

    private void journal(long instruction, String operation, ArrayList<String> parameters) {
        // System.out.println(instruction + " " + operation + " - " + String.join("-", parameters));
    }

    private long getParameters(int mode, int pos) {
        return switch (mode) {
            case 0 -> intCode[(int) intCode[pos]];
            case 1 -> intCode[pos];
            default -> intCode[relativeBase + (int) intCode[pos]];
        };
    }

    private int getAddress(int mode, int pos) {
        if (mode == 0) {
            return (int) intCode[pos];
        } else {
            return relativeBase + (int) intCode[pos];
        }
    }

    private long[] stringToIntCode (String memory) {
        return Arrays.stream(memory.split(","))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    private long[] stringToIntCode (String memory, int memorySize) {
        long[] result = new long[memorySize];
        int i = 0;
        for (String s : memory.split(",")) {
            result[i] = Long.parseLong(s);
            i++;
        }
        return result;
    }
}
