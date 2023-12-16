package calendar.year._2023.day12;

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

public class HotSprings extends Exercise {

    public HotSprings(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<SpringsReport> springsReports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern digitPattern = Pattern.compile("\\d+");
            while (null != (line = br.readLine())) {
                String[] dataParts = line.split(" ");
                List<Integer> groupSizes = digitPattern.matcher(dataParts[1]).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();
                springsReports.add(new SpringsReport(dataParts[0], groupSizes));
            }
        }

        long result = springsReports.stream()
                .map(SpringsReport::countLegitArrangements)
                .reduce(Long::sum)
                .orElseThrow();

        return print(result);
    }
}
