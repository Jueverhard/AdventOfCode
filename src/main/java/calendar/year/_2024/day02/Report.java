package calendar.year._2024.day02;

import java.util.List;

public record Report(List<Integer> values) {

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

    private boolean isDeltaIllicit(int delta, int sign) {
        return delta * sign <= 0 || delta * sign > 3;
    }
}
