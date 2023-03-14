package calendar.year._2016.day01;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NoTimeForATaxicab extends Exercise {
    public NoTimeForATaxicab(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String input = br.readLine();
            String[] instructionStrings = input.split(", ");
            List<Instruction> instructions = Arrays.stream(instructionStrings)
                    .map(Instruction::new)
                    .collect(Collectors.toList());

            int distance = Part.PART_1.equals(part) ? exercise1(instructions) : exercise2(instructions);
            return print(distance);
        }
    }

    private int exercise1(List<Instruction> instructions) {
        Position position = new Position(0, 0, Direction.N);

        for (Instruction instruction : instructions) {
            position.move(instruction);
        }

        return position.computeDistanceToOrigin();
    }

    private int exercise2(List<Instruction> instructions) {
        Position position = new Position(0, 0, Direction.N);
        List<Position> visitedPositions = new ArrayList<>();
        visitedPositions.add(position.copyOf());

        for (Instruction instruction : instructions) {
            List<Position> newVisitedPositions = position.moveAndReturnPositions(instruction);

            for (Position newPos : newVisitedPositions) {
                Optional<Position> alreadyVisitedPosition = visitedPositions.stream()
                        .filter(visitedPos -> visitedPos.getX() == newPos.getX() && visitedPos.getY() == newPos.getY())
                        .findAny();

                if (alreadyVisitedPosition.isPresent()) {
                    return alreadyVisitedPosition.get().computeDistanceToOrigin();
                }
            }
            visitedPositions.addAll(newVisitedPositions);
        }

        throw new IllegalStateException("No position was visited twice");
    }
}
