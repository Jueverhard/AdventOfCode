package calendar.year._2016.day10;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Bot implements Target {

    @Getter
    private final int id;

    private final List<Integer> values;

    @Getter
    private final int lowTargetId;

    @Getter
    private final boolean isLowTargetAnOutput;

    @Getter
    private final int highTargetId;

    @Getter
    private final boolean isHighTargetAnOutput;

    private Target lowTarget;

    private Target highTarget;

    private static List<Integer> lookedUpValues;

    public static void setUpLookedUpValues(List<Integer> lookedUpValues) {
        Bot.lookedUpValues = lookedUpValues;
    }

    public Bot(int id, int lowTargetId, int highTargetId, boolean isLowTargetAnOutput, boolean isHighTargetAnOutput) {
        this.id = id;
        this.values = new ArrayList<>();
        this.lowTargetId = lowTargetId;
        this.highTargetId = highTargetId;
        this.isLowTargetAnOutput = isLowTargetAnOutput;
        this.isHighTargetAnOutput = isHighTargetAnOutput;
    }

    public void initialize(Map<Integer, Bot> botById, Map<Integer, Output> outputById) {
        if (null != this.lowTarget || null != this.highTarget) {
            throw new IllegalStateException("Bot already initialized");
        }

        this.lowTarget = isLowTargetAnOutput ? outputById.get(lowTargetId) : botById.get(lowTargetId);
        this.highTarget = isHighTargetAnOutput ? outputById.get(highTargetId) : botById.get(highTargetId);
    }

    @Override
    public Optional<Integer> addValue(int value) {
        values.add(value);

        if (2 != values.size()) {
            return Optional.empty();
        }

        List<Integer> foundBotIds = new ArrayList<>();

        // Checks whether the researched bot was found
        if (values.containsAll(lookedUpValues)) {
            foundBotIds.add(id);
        }
        lowTarget.addValue(Collections.min(values))
                .ifPresent(foundBotIds::add);
        highTarget.addValue(Collections.max(values))
                .ifPresent(foundBotIds::add);

        return foundBotIds.stream().findFirst();
    }
}
