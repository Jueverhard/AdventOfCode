package calendar.year._2023.day04;

import java.util.Set;

public record Card(Set<Integer> winningNumbers, Set<Integer> possessedNumbers) {

    public int computeScore() {
        long nbWinningCards = winningNumbers.stream()
                .filter(possessedNumbers::contains)
                .count();

        return (int) Math.pow(2, nbWinningCards - 1d);
    }
}
