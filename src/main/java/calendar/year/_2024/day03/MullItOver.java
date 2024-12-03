package calendar.year._2024.day03;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MullItOver extends Exercise {

    public MullItOver(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Multiplication> multiplications = new ArrayList<>();

        // Data initialization
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern multiplierPattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            while (null != (line = br.readLine())) {
                List<Multiplication> inputMultiplications = multiplierPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Multiplication::new)
                        .toList();

                multiplications.addAll(inputMultiplications);
            }
        }

        int result = multiplications.stream()
                .map(Multiplication::compute)
                .reduce(Integer::sum)
                .orElseThrow();

        return print(result);
    }
}
