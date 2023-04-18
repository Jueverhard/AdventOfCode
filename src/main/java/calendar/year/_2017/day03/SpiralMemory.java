package calendar.year._2017.day03;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class SpiralMemory extends Exercise {
    public SpiralMemory(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        int input;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            input = Integer.parseInt(br.readLine());
        }

        if (Part.PART_1.equals(part)) {
            Spiral spiral = new Spiral(input);
            return print(spiral.computeDistanceFromCenter(input));
        } else {
            SpiralPart2 spiral = new SpiralPart2(input);
            return print(spiral.getFirstValueGreaterThan(input));
        }
    }
}
