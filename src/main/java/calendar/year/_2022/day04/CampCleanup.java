package calendar.year._2022.day04;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class CampCleanup extends Exercise {

    public CampCleanup(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int containedPairs = 0;
            while ((line = br.readLine()) != null) {
                String[] sections = line.split(",");
                String[] leftSections = sections[0].split("-");
                String[] rightSections = sections[1].split("-");
                int leftStart = Integer.parseInt(leftSections[0]);
                int leftEnd = Integer.parseInt(leftSections[1]);
                int rightStart = Integer.parseInt(rightSections[0]);
                int rightEnd = Integer.parseInt(rightSections[1]);
                boolean pairsOverlap;
                if (Part.PART_1.equals(part)) {
                    pairsOverlap = (leftStart <= rightStart && leftEnd >= rightEnd) ||
                            (rightStart <= leftStart && rightEnd >= leftEnd);
                } else {
                    pairsOverlap = (leftStart <= rightStart && leftEnd >= rightStart) ||
                            (rightStart <= leftStart && rightEnd >= leftStart);
                }
                if (pairsOverlap) {
                    containedPairs++;
                }
            }

            return print(containedPairs);
        }
    }
}
