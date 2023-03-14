package calendar.year._2021.day02;

import calendar.year.enums.Part;
import utils.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Dive extends Exercise {
    public Dive(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Position submarinePosition = new Position(0, 0);
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                String[] instructions = line.split(" ");
                Direction direction = Direction.fromValue(instructions[0]);
                int units = Integer.parseInt(instructions[1]);
                submarinePosition.move(direction, units);
            }
        }
        return print(submarinePosition.getX() * submarinePosition.getDepth());
    }
}
