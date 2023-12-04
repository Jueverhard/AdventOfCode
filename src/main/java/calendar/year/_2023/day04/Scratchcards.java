package calendar.year._2023.day04;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scratchcards extends Exercise {

    public Scratchcards(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Map<Card, Integer> nbOfCards = new TreeMap<>(Comparator.comparing(Card::id));
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern digitPattern = Pattern.compile("\\d+");
            String line;
            while (null != (line = br.readLine())) {
                String[] dataParts = line.split(":|\\|");
                Matcher idMatcher = digitPattern.matcher(dataParts[0]);
                if (!idMatcher.find()) {
                    throw new IllegalArgumentException();
                }
                int id = Integer.parseInt(idMatcher.group());

                Set<Integer> winningNumbers = digitPattern.matcher(dataParts[1]).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                Set<Integer> possessedNumbers = digitPattern.matcher(dataParts[2]).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                nbOfCards.put(new Card(id, winningNumbers, possessedNumbers), 1);
            }
        }

        int result;
        if (Part.PART_1 == part) {
            result = nbOfCards.keySet().stream()
                    .map(Card::computeNbWinningCards)
                    .map(nbOfWinningCards -> (int) Math.pow(2, nbOfWinningCards - 1))
                    .reduce(0, Integer::sum);
        } else {
            for (int i = 1; i < nbOfCards.size(); i++) {
                Map.Entry<Card, Integer> currentEntry = extractCard(nbOfCards, i);
                int nbOfWinningCards = currentEntry.getKey().computeNbWinningCards();

                IntStream.rangeClosed(i + 1, i + nbOfWinningCards)
                        .forEach(idCard -> {
                            Map.Entry<Card, Integer> entry = extractCard(nbOfCards, idCard);
                            entry.setValue(entry.getValue() + currentEntry.getValue());
                        });
            }

            result = nbOfCards.values().stream()
                    .reduce(0, Integer::sum);
        }

        return print(result);
    }

    private Map.Entry<Card, Integer> extractCard(Map<Card, Integer> nbOfCards, int cardId) {
        return nbOfCards.entrySet().stream()
                .filter(entry -> cardId == entry.getKey().id())
                .findFirst().orElseThrow();
    }
}
