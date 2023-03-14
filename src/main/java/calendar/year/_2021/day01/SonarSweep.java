package calendar.year._2021.day01;

import utils.enums.Part;
import utils.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Queue;

public class SonarSweep extends Exercise {
    public SonarSweep(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        int nbIncreasingTimes = 0;
        int nbMeasuresExpected = Part.PART_1.equals(part) ? 1 : 3;
        Queue<Integer> previousMeasures = new ArrayDeque<>(nbMeasuresExpected + 1);
        Queue<Integer> measures = new ArrayDeque<>(nbMeasuresExpected + 1);
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                // Adds the last measure to the measures queue
                int measure = Integer.parseInt(line);
                measures.add(measure);

                // If there is enough measures, analyze them
                if (measures.size() > nbMeasuresExpected) {
                    // Removes the first measure, which has "expired"
                    measures.remove();

                    // Checks if the new set of measures is greater than the previous one
                    if (sumMeasures(measures) > sumMeasures(previousMeasures)) {
                        nbIncreasingTimes++;
                    }
                }

                // Updates the queue of previous measures
                previousMeasures.add(measure);
                if (previousMeasures.size() > nbMeasuresExpected) {
                    previousMeasures.remove();
                }
            }

        }
        return print(nbIncreasingTimes);
    }

    private int sumMeasures(Queue<Integer> measures) {
        return measures.stream()
                .mapToInt(elt -> elt)
                .sum();
    }
}
