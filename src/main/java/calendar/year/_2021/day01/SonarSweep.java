package calendar.year._2021.day01;

import calendar.year.enums.Part;
import utils.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class SonarSweep extends Exercise {
    public SonarSweep(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        int nbIncreasingTimes = 0;
        Optional<Integer> previousDepth = Optional.empty();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                int depth = Integer.parseInt(line);
                if (previousDepth.isPresent() && depth > previousDepth.get()) {
                    nbIncreasingTimes++;
                }
                previousDepth = Optional.of(depth);
            }

        }
        return print(nbIncreasingTimes);
    }
}
