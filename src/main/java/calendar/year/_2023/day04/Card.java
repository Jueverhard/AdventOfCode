package calendar.year._2023.day04;

import java.util.Set;

public record Card(int id, Set<Integer> winningNumbers, Set<Integer> possessedNumbers) {

    public int computeNbWinningCards() {
        return (int) winningNumbers.stream()
                .filter(possessedNumbers::contains)
                .count();
    }
}
