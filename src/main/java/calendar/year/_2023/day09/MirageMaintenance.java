package calendar.year._2023.day09;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MirageMaintenance extends Exercise {

    public MirageMaintenance(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Sequence> sequences = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern pattern = Pattern.compile("-?\\d+");
            while (null != (line = br.readLine())) {
                List<Integer> numbers = pattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();
                sequences.add(new Sequence(numbers));
            }
        }

        int result = sequences.stream()
                .map(Sequence::computeNextValue)
                .reduce(0, Integer::sum);

        return print(result);
    }
}
