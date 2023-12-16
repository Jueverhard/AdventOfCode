package calendar.year._2023.day15;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LensLibrary extends Exercise {

    public LensLibrary(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Step> steps;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String input = br.readLine();
            steps = Arrays.stream(input.split(","))
                    .map(Step::new)
                    .toList();
        }

        int result = steps.stream()
                .map(Step::computeHashValue)
                .reduce(0, Integer::sum);
        return print(result);
    }
}
