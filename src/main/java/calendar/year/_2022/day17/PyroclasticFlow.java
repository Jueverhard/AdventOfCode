package calendar.year._2022.day17;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PyroclasticFlow extends Exercise {

    public PyroclasticFlow(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initialize the rocks
        Rock horizontalLine = new Rock(IntStream.range(0, 4).mapToObj(x -> new Position(x, 0)).collect(Collectors.toSet()));
        Rock cross = new Rock(Set.of(
                new Position(1, 0),
                new Position(0, 1),
                new Position(1, 1),
                new Position(2, 1),
                new Position(1, 2)
        ));
        Rock angle = new Rock(Set.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(2, 0),
                new Position(2, 1),
                new Position(2, 2)
        ));
        Rock verticalLine = new Rock(IntStream.range(0, 4).mapToObj(y -> new Position(0, y)).collect(Collectors.toSet()));
        Rock square = new Rock(Set.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(0, 1),
                new Position(1, 1)
        ));

        // Initialize the rock chamber
        RockChamber rockChamber;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            rockChamber = new RockChamber(
                    List.of(horizontalLine, cross, angle, verticalLine, square),
                    br.readLine().split("")
            );
        }

        long nbRocks = 0L;
        long limit = Part.PART_1.equals(part) ? 2022 : 1000000000000L;
        // TODO JEV : rework the solution to compute more efficiently to support exercise 2
        while (nbRocks < limit) {
            rockChamber.dropRock();
            nbRocks++;
        }

        return print(rockChamber.computeHeight());
    }
}
