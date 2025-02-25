package calendar.year._2024.day08;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResonantCollinearity extends Exercise {

    public ResonantCollinearity(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        List<String> inputLines = Parser.parseLines(this.getInputPath(testMode));
        Set<Antenna> antennas = IntStream.range(0, inputLines.size())
                .mapToObj(y -> IntStream.range(0, inputLines.get(y).length())
                        .filter(x -> inputLines.get(y).charAt(x) != '.')
                        .mapToObj(x -> new Antenna(new Position(x, y), inputLines.get(y).charAt(x)))
                        .collect(Collectors.toSet())
                )
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        int maxX = inputLines.get(0).length() - 1;
        int maxY = inputLines.size() - 1;

        Map<Character, Set<Position>> antennaPositionsPerFrequency = antennas.stream()
                .collect(Collectors.groupingBy(
                        Antenna::frequency,
                        Collectors.mapping(Antenna::position, Collectors.toSet())
                ));

        Set<Position> antinodes = antennaPositionsPerFrequency.values().stream()
                .flatMap(positions -> positions.stream()
                        .flatMap(pos1 -> positions.stream()
                                .filter(pos2 -> !pos1.equals(pos2))
                                .map(pos1::computeSymmetry)
                        )
                )
                .filter(antinode -> antinode.isWithinBounds(maxX, maxY))
                .collect(Collectors.toSet());

        int result = antinodes.size();

        return print(result);
    }
}
