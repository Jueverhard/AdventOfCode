package calendar.year._2019.day08;

import java.util.List;

public record Layer(List<Integer> digits) {

    public int countZeros() {
        return (int) digits.stream()
                .filter(digit -> 0 == digit)
                .count();
    }

    public long computeValue() {
        return digits.stream()
                .filter(digit -> 1 == digit)
                .count() *
                digits.stream()
                        .filter(digit -> 2 == digit)
                        .count();
    }
}
