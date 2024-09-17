package calendar.year._2020.day08;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandheldHalting extends Exercise {
    public HandheldHalting(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<String> operations = Parser.parseLines(getInputPath(testMode));
        int accumulator = 0;
        int currentIndex = 0;
        Set<Integer> visitedIndexes = new HashSet<>();

        while (visitedIndexes.add(currentIndex)) {
            String[] instructionData = operations.get(currentIndex).split("\\s");
            Operation operation = Operation.valueOf(instructionData[0].toUpperCase());

            switch (operation) {
                case ACC -> {
                    accumulator += Integer.parseInt(instructionData[1]);
                    currentIndex++;
                }
                case JMP -> currentIndex += Integer.parseInt(instructionData[1]);
                case NOP -> currentIndex++;
            }
        }

        return print(accumulator);
    }
}
