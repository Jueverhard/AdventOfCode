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
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InternetProtocolVersion7 extends Exercise {

    public InternetProtocolVersion7(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<IpAddress> ipAddresses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern pattern = Pattern.compile("\\w+");
            while (null != (line = br.readLine())) {
                List<String> words = pattern.matcher(line).results()
                        .map(MatchResult::group)
                        .toList();

                Set<String> outsideWords = IntStream.range(0, words.size())
                        .filter(i -> i % 2 == 0)
                        .mapToObj(words::get)
                        .collect(Collectors.toSet());
                Set<String> insideWords = IntStream.range(0, words.size())
                        .filter(i -> i % 2 == 1)
                        .mapToObj(words::get)
                        .collect(Collectors.toSet());
                ipAddresses.add(new IpAddress(outsideWords, insideWords));
            }
        }

        Predicate<IpAddress> predicate = Part.PART_1 == part ?
                IpAddress::supportsTLS :
                IpAddress::supportsSSL;
        long result = ipAddresses.stream()
                .filter(predicate)
                .count();
        return print(result);
    }
}
