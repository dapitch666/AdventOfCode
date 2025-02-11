package org.anne.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Computer {

    private final long[] intCode = new long[10000];
    private final List<Long> input = new ArrayList<>();
    private final List<Long> output = new ArrayList<>();
    private boolean stopped;
    private int currentInstruction;
    private int relativeBase;

    public Computer(String memory) {
        int i = 0;
        for (String s : memory.split(",")) {
            intCode[i] = Long.parseLong(s);
            i++;
        }
    }

    public long[] getIntCode() {
        return this.intCode;
    }

    public List<Long> getOutputs() {
        return output;
    }

    public long getOutput() {
        if (output.isEmpty()) {
            return 0;
        } else {
            return this.output.removeFirst();
        }
    }

    public long getLastOutput() {
        if (this.output.isEmpty()) {
            return 0;
        } else {
            return this.output.getLast();
        }
    }

    public void compute(long... input) {
        this.writeInput(input);
        compute();
    }
    
    public void compute(String input) {
        this.writeInput(input);
        compute();
    }

    public long computeAndGetOutput(long... input) {
        this.writeInput(input);
        compute();
        return getOutput();
    }
    
    public String computeAndGetOutputAsString(String input) {
        this.writeInput(input);
        compute();
        return getOutputAsString();
    }
    
    public String getOutputAsString() {
        StringBuilder sb = new StringBuilder();
        for (Long value : output) {
            sb.append((char) value.intValue());
        }
        return sb.toString();
    }

    public void writeInput(long... input) {
        for (long i : input) {
            this.input.add(i);
        }
    }
    
    public void writeInput(String input) {
        for (char c : input.toCharArray()) {
            this.input.add((long) c);
        }
    }

    private long readInput() {
        long firstInput = this.input.getFirst();
        this.input.removeFirst();
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
                    this.relativeBase += (int) first;
                    currentInstruction += 2;
                    journal(instruction, "OFFSET", new ArrayList<>(Collections.singletonList(String.valueOf(first))));
                }
            }
        }
    }

    @SuppressWarnings({"EmptyMethod", "unused"})
    private void journal(long instruction, String operation, ArrayList<String> parameters) {
        //System.out.println(instruction + " " + operation + " - " + String.join("-", parameters));
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

    public boolean hasOutput() {
        return !output.isEmpty();
    }
}
