package calendar.year._2024.day07;

import java.util.List;
import java.util.stream.Stream;

public record Operation(long result, List<Long> values) {

    public boolean mayComputesInto() {
        return mayComputesInto(values);
    }

    private boolean mayComputesInto(List<Long> valuesLeft) {
        long a = valuesLeft.get(0);
        long b = valuesLeft.get(1);
        if (2 == valuesLeft.size()) {
            return result == a + b || result == a * b;
        }

        List<Long> subValues = valuesLeft.subList(2, valuesLeft.size());
        List<Long> subValuesWithSum = Stream.concat(Stream.of(a + b), subValues.stream()).toList();
        List<Long> subValuesWithProduct = Stream.concat(Stream.of(a * b), subValues.stream()).toList();

        return mayComputesInto(subValuesWithSum) || mayComputesInto(subValuesWithProduct);
    }
}
