package calendar.year._2015.day02;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class IWasToldThereWouldBeNoMath extends Exercise {
    public IWasToldThereWouldBeNoMath(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Box> boxes = new ArrayList<>();

        // Lecture du fichier
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                Pattern digitPattern = Pattern.compile("\\d+");
                List<Integer> dimensions = digitPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();

                Box box = new Box(dimensions.get(0), dimensions.get(1), dimensions.get(2));
                boxes.add(box);
            }
        }

        Function<Box, Integer> computeFunction = Part.PART_1 == part ?
                Box::computeNeededPaper :
                Box::computePaperForRibbon;

        int result = boxes.stream()
                .map(computeFunction)
                .reduce(0, Integer::sum);

        return print(result);
    }
}
