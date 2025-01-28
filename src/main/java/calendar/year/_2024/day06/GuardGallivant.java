package calendar.year._2024.day06;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        Set<Position> visitedPositions = computeGuardPath(obstacles, guard, mapWidth, currentY);

        int result = Part.PART_1 == part ?
                visitedPositions.size() :
                countInfiniteGuardPathVariations(obstacles, visitedPositions, guard, mapWidth, currentY);

        return print(result);
    }

    /**
     * Computes the path of a Guard object within a bounded area, avoiding obstacles and throwing an exception
     * if the guard ends up in an infinite looping path. The method tracks all positions visited by the guard.
     *
     * @param obstacles A set of positions that represent obstacles in the guard's path. The guard cannot occupy these positions.
     * @param guard     The initial state of the guard, including its position and orientation.
     * @param maxX      The maximum x-coordinate boundary of the area.
     * @param maxY      The maximum y-coordinate boundary of the area.
     * @return A set of all positions visited by the guard during its traversal.
     * @throws InfinitePathException If the guard's traversal results in a repeated path that forms an infinite loop.
     */
    private Set<Position> computeGuardPath(final Set<Position> obstacles, final Guard guard, int maxX, int maxY) throws InfinitePathException {
        Map<Position, Set<Direction>> directionsByVisitedPositions = new HashMap<>();
        Guard actualGuard = guard.copyOf();
        while (!actualGuard.getPosition().isOutOfBounds(maxX, maxY)) {
            if (!directionsByVisitedPositions.containsKey(actualGuard.getPosition())) {
                directionsByVisitedPositions.put(actualGuard.getPosition(), new HashSet<>(Set.of(actualGuard.getOrientation())));
            } else if (!directionsByVisitedPositions.get(actualGuard.getPosition()).add(actualGuard.getOrientation())) {
                throw new InfinitePathException();
            }
            actualGuard.move(obstacles);
        }

        return directionsByVisitedPositions.keySet();
    }

    /**
     * Counts the number of alternative obstacle configurations that result in an infinite looping path
     * for a given guard.
     *
     * @param obstacles           A set of positions that represent existing obstacles in the guard's path.
     * @param potentielObstacles  A set of possible additional positions that could serve as obstacles.
     * @param guard               The initial state of the guard, including its position and orientation.
     * @param maxX                The maximum x-coordinate boundary of the area.
     * @param maxY                The maximum y-coordinate boundary of the area.
     * @return The number of configurations of obstacles (from potential obstacles) that cause the guard
     *         to enter an infinite looping path.
     */
    private int countInfiniteGuardPathVariations(Set<Position> obstacles, Set<Position> potentielObstacles, final Guard guard, int maxX, int maxY) {
        int nbInfiniteVariations = 0;
        Set<Set<Position>> alternativeObstaclesToEvaluate = potentielObstacles.stream()
                .map(newObstaclePosition -> Stream.concat(obstacles.stream(), Stream.of(newObstaclePosition))
                        .collect(Collectors.toSet())
                )
                .collect(Collectors.toSet());
        for (Set<Position> alternativeObstacles : alternativeObstaclesToEvaluate) {
            try {
                computeGuardPath(alternativeObstacles, guard, maxX, maxY);
            } catch (InfinitePathException e) {
                nbInfiniteVariations++;
            }
        }
        return nbInfiniteVariations;
    }
}
