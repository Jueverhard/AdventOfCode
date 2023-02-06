package calendar.year._2022.day09;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class RopeBridge extends Exercise {
    public RopeBridge(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Rope rope = Part.PART_1.equals(part) ? new RopePart1() : new RopePart2();
            while ((line = br.readLine()) != null) {
                String[] instructions = line.split(" ");
                Direction direction = Direction.valueOf(instructions[0]);
                int nbMoves = Integer.parseInt(instructions[1]);
                rope.moveHead(direction, nbMoves);
            }

            return print(rope.getNbVisitedPositions());
        }
    }
}
