package calendar.year._2022.day04;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CampCleanup extends Exercise {

    public CampCleanup(LocalDate date) {
        super(date);
    }

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int containedPairs = 0;
            while ((line = br.readLine()) != null) {
                List<Integer> sectionsIdentifiers = extractSectionsIdentifiers(line);
                int leftStart = sectionsIdentifiers.get(0);
                int leftEnd = sectionsIdentifiers.get(1);
                int rightStart = sectionsIdentifiers.get(2);

                int rightEnd = sectionsIdentifiers.get(3);
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

    private List<Integer> extractSectionsIdentifiers(String input) {
        Matcher sectionsMatcher = NUMBER_PATTERN.matcher(input);
        List<Integer> sectionsIdentifiers = new ArrayList<>();
        while (sectionsMatcher.find()) {
            sectionsIdentifiers.add(Integer.parseInt(sectionsMatcher.group()));
        }
        return sectionsIdentifiers;
    }
}
