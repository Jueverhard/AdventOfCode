package calendar.year._2023.day04;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Scratchcards extends Exercise {

    public Scratchcards(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Card> cards = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern digitPattern = Pattern.compile("\\d+");
            String line;
            while (null != (line = br.readLine())) {
                String[] dataParts = line.split("\\|");
                Set<Integer> winningNumbers = digitPattern.matcher(dataParts[0]).results()
                        .skip(1)
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                Set<Integer> possessedNumbers = digitPattern.matcher(dataParts[1]).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                cards.add(new Card(winningNumbers, possessedNumbers));
            }
        }

        int result = cards.stream()
                .map(Card::computeScore)
                .reduce(0, Integer::sum);
        return print(result);
    }
}
