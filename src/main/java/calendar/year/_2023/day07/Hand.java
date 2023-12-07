package calendar.year._2023.day07;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Hand(List<Kind> cards, int bid, boolean useJokers) implements Comparable<Hand> {
    private HandType evaluateHand() {
        Map<Kind, Long> nbOccurrencesOfKind = cards.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (useJokers) {
            // Use all jokers to increment the most frequent kind of card
            long nbJokers = nbOccurrencesOfKind.getOrDefault(Kind.J, 0L);
            nbOccurrencesOfKind.replace(Kind.J, 0L);
            nbOccurrencesOfKind.entrySet().stream()
                    .max(Entry.comparingByValue())
                    .stream().findFirst()
                    .ifPresent(entry -> entry.setValue(entry.getValue() + nbJokers));
        }
        int maxOccurrences = Math.toIntExact(Collections.max(nbOccurrencesOfKind.values()));

        return switch (maxOccurrences) {
            case 5 -> HandType.FIVE_OF_A_KIND;
            case 4 -> HandType.FOUR_OF_A_KIND;
            case 3 -> nbOccurrencesOfKind.containsValue(2L) ?
                    HandType.FULL_HOUSE :
                    HandType.THREE_OF_A_KIND;
            case 2 -> 2 == nbOccurrencesOfKind.values().stream()
                    .filter(occurrences -> 2L == occurrences)
                    .count() ?
                    HandType.TWO_PAIRS :
                    HandType.PAIR;
            case 1 -> HandType.HIGH_CARD;
            default -> throw new IllegalStateException("Unexpected value: " + maxOccurrences);
        };
    }

    @Override
    public int compareTo(Hand o) {
        int comparingHand = this.evaluateHand().compareTo(o.evaluateHand());
        if (0 != comparingHand) {
            return comparingHand;
        }

        int i = 0;
        int comparingCard = 0;
        BiFunction<Kind, Kind, Integer> compareFunction = useJokers ?
                Kind::compareUsingJokers :
                Enum::compareTo;

        while (0 == comparingCard && i < cards.size()) {
            comparingCard = compareFunction.apply(cards.get(i), o.cards().get(i));
            i++;
        }

        return comparingCard;
    }
}
