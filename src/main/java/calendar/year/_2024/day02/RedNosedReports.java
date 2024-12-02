package calendar.year._2024.day02;

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

public class RedNosedReports extends Exercise {

    public RedNosedReports(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Report> reports = new ArrayList<>();

        // Data initialization
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern numberPattern = Pattern.compile("\\d+");
            while (null != (line = br.readLine())) {
                List<Integer> inputParts = numberPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();
                reports.add(new Report(inputParts));
            }
        }

        long result = reports.stream()
                .filter(Report::isSafe)
                .count();

        return print(result);
    }
}
