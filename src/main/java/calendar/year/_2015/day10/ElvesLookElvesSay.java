package calendar.year._2015.day10;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElvesLookElvesSay extends Exercise {
    public ElvesLookElvesSay(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        List<Integer> digits = Arrays.stream(Parser.parseLines(this.getInputPath(testMode)).get(0).split(""))
                .map(Integer::parseInt)
                .toList();

        int nbIterations;
        if (testMode) {
            nbIterations = 5;
        } else {
            nbIterations = Part.PART_1 == part ? 40 : 50;
        }

        // Counts digits groups
        for (int i = nbIterations; i > 0; i--) {
            digits = countDigits(digits);
        }

        return print(digits.size());
    }

    private List<Integer> countDigits(List<Integer> digits) {
        List<Integer> result = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < digits.size(); i++) {
            if (digits.get(i).equals(digits.get(i - 1))) {
                count++;
            } else {
                result.add(count);
                result.add(digits.get(i - 1));
                count = 1;
            }
        }
        result.add(count);
        result.add(digits.get(digits.size() - 1));
        return result;
    }
}
