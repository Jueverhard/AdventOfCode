package calendar.year._2024.day07;

import java.util.List;
import java.util.stream.Stream;

public record Operation(long result, List<Long> values) {

    /**
     * @param authorizeTruncation Whether truncation should be authorized.
     * @return Whether the operation values may compute into the operation result.
     */
    public boolean mayComputesInto(boolean authorizeTruncation) {
        return mayComputesInto(values, authorizeTruncation);
    }

    /**
     * Recursive computation of the operation values computability into its result.
     *
     * @param valuesLeft          The values that are still to compute.
     * @param authorizeTruncation Whether truncation should be authorized.
     * @return Whether the operation values may compute into the operation result.
     */
    private boolean mayComputesInto(List<Long> valuesLeft, boolean authorizeTruncation) {
        long a = valuesLeft.get(0);
        long b = valuesLeft.get(1);
        if (2 == valuesLeft.size()) {
            return result == a + b || result == a * b || (authorizeTruncation && result == truncate(a, b));
        }

        List<Long> subValues = valuesLeft.subList(2, valuesLeft.size());
        List<Long> subValuesWithSum = Stream.concat(Stream.of(a + b), subValues.stream()).toList();
        List<Long> subValuesWithProduct = Stream.concat(Stream.of(a * b), subValues.stream()).toList();
        List<Long> subValuesWithTruncation = Stream.concat(Stream.of(truncate(a, b)), subValues.stream()).toList();

        return mayComputesInto(subValuesWithSum, authorizeTruncation) ||
                mayComputesInto(subValuesWithProduct, authorizeTruncation) ||
                (authorizeTruncation && mayComputesInto(subValuesWithTruncation, true));
    }

    /**
     * Concatenates two long values (ex: `truncates(42, 13)` gives 4213).
     *
     * @param a Some value.
     * @param b Some value.
     * @return the concatenated result.
     */
    private long truncate(long a, long b) {
        return Long.parseLong(a + String.valueOf(b));
    }
}
