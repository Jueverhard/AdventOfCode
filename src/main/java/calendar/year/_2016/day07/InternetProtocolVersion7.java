package calendar.year._2016.day07;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InternetProtocolVersion7 extends Exercise {

    public InternetProtocolVersion7(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<IpAddress> ipAddresses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern globalPattern = Pattern.compile("\\w+");
            Pattern bracketPattern = Pattern.compile("\\[\\w+\\]");
            while (null != (line = br.readLine())) {
                Set<String> bracketWords = bracketPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .collect(Collectors.toSet());
                Set<String> otherWords = globalPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .filter(word -> !bracketWords.contains(word))
                        .collect(Collectors.toSet());
                ipAddresses.add(new IpAddress(otherWords, bracketWords));
            }
        }

        long result = ipAddresses.stream()
                .filter(IpAddress::isValid)
                .count();
        return print(result);
    }
}
