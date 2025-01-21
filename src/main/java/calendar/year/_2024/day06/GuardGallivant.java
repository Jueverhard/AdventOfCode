package calendar.year._2024.day06;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class GuardGallivant extends Exercise {

    public GuardGallivant(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Position> obstacles = new HashSet<>();
        Guard guard = null;
        int currentY = 0;
        int mapWidth = 0;

        // Data initialization
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                mapWidth = line.length();
                String finalLine = line;
                int finalCurrentY = currentY;
                IntStream.range(0, line.length())
                        .filter(x -> '#' == finalLine.charAt(x))
                        .mapToObj(currentX -> new Position(currentX, finalCurrentY))
                        .forEach(obstacles::add);

                int guardXPosition = line.indexOf('^');
                if (-1 != guardXPosition) {
                    guard = new Guard(new Position(guardXPosition, currentY), Direction.UP);
                }
                currentY++;
            }
        }
        Objects.requireNonNull(guard);

        // Compute the guard path
        Set<Position> visitedPositions = new HashSet<>();
        while (!guard.getPosition().isOutOfBounds(mapWidth, currentY)) {
            visitedPositions.add(guard.getPosition().copyOf());
            guard.move(obstacles);
        }

        return print(visitedPositions.size());
    }
}
