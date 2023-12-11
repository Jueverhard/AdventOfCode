package calendar.year._2023.day11;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CosmicExpansion extends Exercise {

    public CosmicExpansion(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Galaxy> galaxiesWithoutExpansion = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int y = 0;
            while (null != (line = br.readLine())) {
                int x = 0;
                while (x >= 0 && x < line.length()) {
                    x = line.indexOf('#', x);
                    if (x >= 0) {
                        galaxiesWithoutExpansion.add(new Galaxy( new Position(x, y)));
                        x++;
                    }
                }
                y++;
            }
        }

        int expansionSpeed = Part.PART_1 == part ? 1 : 999999;
        List<Galaxy> galaxies = expandGalaxies(galaxiesWithoutExpansion, expansionSpeed);
        long result = IntStream.range(0, galaxies.size())
                .mapToObj(i -> galaxies.subList(i + 1, galaxies.size()).stream()
                        .map(galaxy -> galaxies.get(i).position().computeManhattanDistance(galaxy.position()))
                        .reduce(0L, Long::sum)
                )
                .reduce(0L, Long::sum);

        return print(result);
    }

    private List<Galaxy> expandGalaxies(final Set<Galaxy> galaxies, int expansionSpeed) {
        long xMax = galaxies.stream()
                .max(Comparator.comparingLong(galaxy -> galaxy.position().x()))
                .orElseThrow()
                .position().x();
        long yMax = galaxies.stream()
                .max(Comparator.comparingLong(galaxy -> galaxy.position().y()))
                .orElseThrow()
                .position().y();
        Set<Long> missingXs = LongStream.range(0, xMax)
                .filter(x -> galaxies.stream()
                        .noneMatch(galaxy -> x == galaxy.position().x())
                ).boxed()
                .collect(Collectors.toSet());
        Set<Long> missingYs = LongStream.range(0, yMax)
                .filter(y -> galaxies.stream()
                        .noneMatch(galaxy -> y == galaxy.position().y())
                ).boxed()
                .collect(Collectors.toSet());

        return galaxies.stream()
                .map(galaxy -> new Galaxy(new Position(
                        galaxy.position().x() + expansionSpeed * missingXs.stream()
                                .filter(missingX -> missingX < galaxy.position().x())
                                .count(),
                        galaxy.position().y() + expansionSpeed * missingYs.stream()
                                .filter(missingY -> missingY < galaxy.position().y())
                                .count()
                )))
                .toList();
    }
}
