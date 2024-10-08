package calendar.year._2020.day08;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandheldHalting extends Exercise {
    public HandheldHalting(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Instruction> instructions = Parser.parseLines(getInputPath(testMode)).stream()
                .map(instruction -> instruction.split("\\s"))
                .map(instructionData -> new Instruction(
                        Operation.valueOf(instructionData[0].toUpperCase()),
                        Integer.parseInt(instructionData[1])
                ))
                .toList();

        int res = Part.PART_1 == part ?
                executeInstructions(instructions).accumulatorValue() :
                findFixedInstructionsAccumulator(instructions);
        return print(res);
    }

    public record Result(int accumulatorValue, boolean isFinite) {}

    /**
     * @param instructions Instructions to execute
     * @return The final accumulator value, and whether the set completes
     */
    private Result executeInstructions(List<Instruction> instructions) {
        int accumulator = 0;
        int currentIndex = 0;
        Set<Integer> visitedIndexes = new HashSet<>();

        while (visitedIndexes.add(currentIndex) && currentIndex < instructions.size()) {
            Instruction instruction = instructions.get(currentIndex);

            switch (instruction.operation()) {
                case ACC -> {
                    accumulator += instruction.value();
                    currentIndex++;
                }
                case JMP -> currentIndex += instruction.value();
                case NOP -> currentIndex++;
            }
        }

        return new Result(accumulator, currentIndex >= instructions.size());
    }

    /**
     * Generate the fixed instructions set and compute its final accumulator value
     *
     * @param instructions Instructions to execute
     * @return The resulting accumulator value
     */
    private int findFixedInstructionsAccumulator(List<Instruction> instructions) {
        return instructions.stream()
                // Evicts the `acc` operations
                .filter(instruction -> Operation.ACC != instruction.operation())
                // Generate an alternative instructions list (NOP <-> JMP)
                .map(instruction -> generateAlternativeList(instructions, instruction))
                // Compute the new list result
                .map(this::executeInstructions)
                // Keep only the alternative list that does not end in an infinite loop
                .filter(Result::isFinite)
                .findAny().orElseThrow()
                .accumulatorValue();
    }

    /**
     * Generates an alternative version of the given instructions list, having the given instruction replaced with one
     * with the operation swapped (JMP <-> NOP)
     *
     * @param originalInstructions The original instructions list
     * @param instructionToReplace The instruction to replace
     * @return The mutated instructions list
     */
    private List<Instruction> generateAlternativeList(
            final List<Instruction> originalInstructions,
            Instruction instructionToReplace
    ) {
        List<Instruction> alternativeInstructions = new ArrayList<>(originalInstructions);
        Operation newOperation = Operation.JMP == instructionToReplace.operation() ?
                Operation.NOP :
                Operation.JMP;
        Instruction newInstruction = new Instruction(
                newOperation,
                instructionToReplace.value()
        );
        alternativeInstructions.set(
                originalInstructions.indexOf(instructionToReplace),
                newInstruction
        );
        return alternativeInstructions;
    }
}
