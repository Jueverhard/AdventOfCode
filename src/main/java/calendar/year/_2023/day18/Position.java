package calendar.year._2023.day18;

import java.util.List;
import java.util.stream.IntStream;

public record Position(long x, long y) {

    public Position computeDestination(Direction direction, int range) {
        return switch (direction) {
            case U -> new Position(x, y + range);
            case R -> new Position(x + range, y);
            case D -> new Position(x, y - range);
            case L -> new Position(x - range, y);
        };
    }

    public boolean isToBeDugUp(List<InstructionPosition> instructionPositions) {
        int northCuts = (int) IntStream.range(0, instructionPositions.size())
                // Keeps the instructions above the current position
                .filter(i -> instructionPositions.get(i).position().y() > y)
                // Keeps the instructions crossing the vertical axis
                .filter(i -> doesCrossNorth(
                        instructionPositions.get(i),
                        0 != i ? instructionPositions.get(i - 1) : instructionPositions.get(instructionPositions.size() - 1),
                        instructionPositions.size() - 1 != i ? instructionPositions.get(i + 1) : instructionPositions.get(0)
                ))
                .count();
        int westCuts = (int) IntStream.range(0, instructionPositions.size())
                // Keeps the instructions on the left of the current position
                .filter(i -> instructionPositions.get(i).position().x() < x)
                // Keeps the instructions crossing the horizontal axis
                .filter(i -> doesCrossWest(
                        instructionPositions.get(i),
                        0 != i ? instructionPositions.get(i - 1) : instructionPositions.get(instructionPositions.size() - 1),
                        instructionPositions.size() - 1 != i ? instructionPositions.get(i + 1) : instructionPositions.get(0)
                ))
                .count();

        return 1 == northCuts % 2 && 1 == westCuts % 2 && instructionPositions.stream()
                .noneMatch(instruction ->
                        (instruction.position().x() == x && Direction.U == instruction.instruction().direction() && instruction.position().y() <= y && instruction.position().y() + instruction.instruction().volume() >= y)
                                || (instruction.position().x() == x && Direction.D == instruction.instruction().direction() && instruction.position().y() >= y && instruction.position().y() - instruction.instruction().volume() <= y)
                                || (instruction.position().y() == y && Direction.R == instruction.instruction().direction() && instruction.position().x() <= x && instruction.position().x() + instruction.instruction().volume() >= x)
                                || (instruction.position().y() == y && Direction.L == instruction.instruction().direction() && instruction.position().x() >= x && instruction.position().x() - instruction.instruction().volume() <= x)
                );
    }

    private boolean doesCrossNorth(InstructionPosition instructionPosition, InstructionPosition previous, InstructionPosition next) {
        boolean crossFromRight = instructionPosition.position().x() > x
                && Direction.L == instructionPosition.instruction().direction()
                && instructionPosition.position().x() - instructionPosition.instruction().volume() < x;
        boolean crossFromLeft = instructionPosition.position().x() < x
                && Direction.R == instructionPosition.instruction().direction()
                && instructionPosition.position().x() + instructionPosition.instruction().volume() > x;
        boolean joinCrossingInstructions = instructionPosition.position().x == x
                && instructionPosition.instruction().direction().isVertical()
                && previous.instruction().direction().isHorizontal()
                && next.instruction().direction().isHorizontal()
                && previous.instruction().direction() == next.instruction().direction();

        return crossFromRight || crossFromLeft || joinCrossingInstructions;
    }

    private boolean doesCrossWest(InstructionPosition instructionPosition, InstructionPosition previous, InstructionPosition next) {
        boolean crossFromTop = instructionPosition.position().y() > y
                && Direction.D == instructionPosition.instruction().direction()
                && instructionPosition.position().y() - instructionPosition.instruction().volume() < y;;
        boolean crossFromBottom = instructionPosition.position().y() < y
                && Direction.U == instructionPosition.instruction().direction()
                && instructionPosition.position().y() + instructionPosition.instruction().volume() > y;
        boolean joinCrossingInstructions = instructionPosition.position().y == y
                && instructionPosition.instruction().direction().isHorizontal()
                && previous.instruction().direction().isVertical()
                && next.instruction().direction().isVertical()
                && previous.instruction().direction() == next.instruction().direction();

        return crossFromTop || crossFromBottom || joinCrossingInstructions;
    }
}
