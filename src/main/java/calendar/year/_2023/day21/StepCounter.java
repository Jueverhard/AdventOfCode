package calendar.year._2023.day21;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class StepCounter extends Exercise {

    public StepCounter(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Position> rocks = new HashSet<>();
        Position start = null;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int y = 0;
            while (null != (line = br.readLine())) {
                for (int x = 0; x < line.length(); x++) {
                    if ('#' == line.charAt(x)) {
                        rocks.add(new Position(x, y));
                    } else if ('S' == line.charAt(x)) {
                        start = new Position(x, y);
                    }
                }
                y++;
            }
            Objects.requireNonNull(start);
        }

        int nbSteps = testMode ? 6 : 64;
        Set<Position> positions = new HashSet<>(Set.of(start));
        while (nbSteps > 0) {
            positions = positions.stream()
                    .map(position -> position.computeReachablePositions(rocks))
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
            nbSteps--;
        }

        int result = positions.size();
        return print(result);
    }
}
