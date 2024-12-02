package calendar.year._2024.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public record Report(List<Integer> values) {

    /**
     * Evaluates whether the report is "safe" based on arithmetic controls over its values.
     *
     * @return Whether the report is safe.
     */
    public boolean isSafe() {
        int delta = values.get(1) - values.get(0);
        final int sign = delta > 0 ? 1 : -1;
        int currentValue = values.get(0);

        for (int i = 1; i < values.size(); i++) {
            delta = values.get(i) - currentValue;
            if (isDeltaIllicit(delta, sign)) {
                return false;
            }
            currentValue = values.get(i);
        }
        return true;
    }

    /**
     * Evaluates whether the report is "safe" based on arithmetic controls over its values, Problem Dampener included
     * (i.e. no more than one value may be illicit).
     *
     * @return Whether the report is safe.
     */
    public boolean isProblemDampenerSafe() {
        return this.isSafe() || generateDampenedReports().stream()
                .anyMatch(Report::isSafe);
    }

    /**
     * Checks whether the given delta is considered illicit based on its value and expected sign.
     *
     * @param delta The change in value to be evaluated.
     * @param sign The expected direction of change.
     * @return Whether the delta is considered illicit.
     */
    private boolean isDeltaIllicit(int delta, int sign) {
        return delta * sign <= 0 || delta * sign > 3;
    }

    /**
     * Computes every version of the report reduced by one value.
     *
     * @return The alternative reports.
     */
    private List<Report> generateDampenedReports() {
        return IntStream.range(0, values.size())
                .mapToObj(i -> {
                    List<Integer> tmp = new ArrayList<>(values);
                    tmp.remove(i);
                    return new Report(tmp);
                })
                .toList();
    }
}
