package calendar.year._2022.day14;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegolithReservoir extends Exercise {

    public RegolithReservoir(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Cave cave = new Cave(new Position(500, 0));
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Position> explicitRockPositions = Arrays.stream(line.split(" -> "))
                        .map(Position::new)
                        .collect(Collectors.toList());

                cave.getBlocks().putAll(defineRockBlocks(explicitRockPositions));
            }
        }

        // Fills the cave with air, then sand
        if (Part.PART_1.equals(part)) {
            cave.fillWithAir();
            cave.fillWithSand();
        } else {
            cave.fillWithAirPart2();
            cave.fillWithSandPart2();
        }

        System.out.println(cave);

        return print(cave.getNbSandBlocks());
    }

    private Map<Position, BlockType> defineRockBlocks(List<Position> explicitPositions) {
        List<Position> implicitPositions = new ArrayList<>(explicitPositions);

        Position previousPos = explicitPositions.get(0);
        for (int i = 1; i < explicitPositions.size(); i++) {
            Position position = explicitPositions.get(i);
            implicitPositions.addAll(previousPos.generateSeparatingPositions(position));
            previousPos = position;
        }

        return implicitPositions.stream()
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        position -> BlockType.ROCK
                ));
    }
}
