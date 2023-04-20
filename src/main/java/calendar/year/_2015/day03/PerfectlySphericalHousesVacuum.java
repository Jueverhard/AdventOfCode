package calendar.year._2015.day03;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PerfectlySphericalHousesVacuum extends Exercise {
    public PerfectlySphericalHousesVacuum(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        int nbVisitedHouses = Part.PART_1.equals(part) ? exercise1(testMode) : exercise2(testMode);
        return print(nbVisitedHouses);

    }

    private int exercise1(boolean testMode) throws IOException {
        Set<Position> visitedPositions = new HashSet<>();
        Position currentPosition = new Position(0, 0);
        visitedPositions.add(currentPosition.copyOf());
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String input = br.readLine();
            for (char move : input.toCharArray()) {
                Direction direction = Direction.fromValue(move);
                currentPosition.move(direction);
                visitedPositions.add(currentPosition.copyOf());
            }
        }
        return visitedPositions.size();
    }

    private int exercise2(boolean testMode) throws IOException {
        Set<Position> visitedPositions = new HashSet<>();
        Position currentSantaPosition = new Position(0, 0);
        Position currentRobotPosition = new Position(0, 0);
        visitedPositions.add(currentSantaPosition.copyOf());
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String input = br.readLine();
            boolean isSantaTurn = true;
            for (char move : input.toCharArray()) {
                Direction direction = Direction.fromValue(move);
                Position positionToUpdate = isSantaTurn ? currentSantaPosition : currentRobotPosition;
                positionToUpdate.move(direction);
                visitedPositions.add(positionToUpdate.copyOf());
                isSantaTurn = !isSantaTurn;
            }
        }
        return visitedPositions.size();
    }
}
