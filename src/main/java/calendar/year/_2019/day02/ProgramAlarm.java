package calendar.year._2019.day02;

import calendar.year.enums.Part;
import utils.Exercise;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramAlarm extends Exercise {
    public ProgramAlarm(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        String input = Parser.parseLines(this.getInputPath(testMode)).get(0);

        // Working list initialization
        List<Integer> integers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        if (Part.PART_1.equals(part) && !testMode) {
             integers.set(1, 12);
             integers.set(2, 2);
        }

        int result = Part.PART_1.equals(part) ? exercise1(integers) : exercise2(integers);
        return print(result);
    }

    private int exercise1(List<Integer> integers) {
        int i = 0;
        OpCode opCode = null;
        while (i < integers.size() && !OpCode.END.equals(opCode)) {
            opCode = executeInstruction(i, integers);
            i += 4;
        }
        return integers.get(0);
    }

    private int exercise2(List<Integer> integers) {
        int expectedOutput = 19690720;
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                List<Integer> modifiedIntegers = new ArrayList<>(integers);
                modifiedIntegers.set(1, noun);
                modifiedIntegers.set(2, verb);
                if (expectedOutput == exercise1(modifiedIntegers)) {
                    return 100 * noun + verb;
                }
            }
        }
        throw new IllegalArgumentException("There is no noun and verb combination leading to the expected output");
    }

    private OpCode executeInstruction(int opCodeIndex, List<Integer> integers) {
        OpCode opCode = OpCode.fromValue(integers.get(opCodeIndex));
        switch (opCode) {
            case ADD:
                add(opCodeIndex, integers);
                break;
            case MUL:
                multiply(opCodeIndex, integers);
                break;
            case END:
                // There is nothing to do here
                break;
        }
        return opCode;
    }

    private void add(int opCodeIndex, List<Integer> integers) {
        int firstValueIndex = integers.get(opCodeIndex + 1);
        int secondValueIndex = integers.get(opCodeIndex + 2);
        int ouputIndex = integers.get(opCodeIndex + 3);

        integers.set(ouputIndex, integers.get(firstValueIndex) + integers.get(secondValueIndex));
    }

    private void multiply(int opCodeIndex, List<Integer> integers) {
        int firstValueIndex = integers.get(opCodeIndex + 1);
        int secondValueIndex = integers.get(opCodeIndex + 2);
        int ouputIndex = integers.get(opCodeIndex + 3);

        integers.set(ouputIndex, integers.get(firstValueIndex) * integers.get(secondValueIndex));
    }
}
