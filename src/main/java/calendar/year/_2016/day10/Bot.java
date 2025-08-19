package calendar.year._2016.day10;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Bot {

    @Getter
    private final int id;

    private final List<Integer> values;

    private final int lowBotId;

    private final int highBotId;

    private Bot lowBot;

    private Bot highBot;

    private static List<Integer> lookedUpValues;

    public static void setUpLookedUpValues(List<Integer> lookedUpValues) {
        Bot.lookedUpValues = lookedUpValues;
    }

    public Bot(int id, int lowBotId, int highBotId) {
        this.id = id;
        this.values = new ArrayList<>();
        this.lowBotId = lowBotId;
        this.highBotId = highBotId;
    }

    public void initialize(Map<Integer, Bot> botById) {
        if (null != this.lowBot || null != this.highBot) {
            throw new IllegalStateException("Bot already initialized");
        }

        this.lowBot = botById.get(lowBotId);
        this.highBot = botById.get(highBotId);
    }

    /**
     * @param value Value to add to the bot.
     * @return The identifier of the looked for bot, if found.
     */
    public Optional<Integer> addValue(int value) {
        values.add(value);

        if (2 != values.size()) {
            return Optional.empty();
        }

        // Checks whether the researched bot was found
        if (values.containsAll(lookedUpValues)) {
            return Optional.of(id);
        }

        Optional<Integer> optFoundBotId = lowBot.addValue(Collections.min(values));
        if (optFoundBotId.isPresent()) {
            return optFoundBotId;
        }

        optFoundBotId = highBot.addValue(Collections.max(values));
        return optFoundBotId;
    }
}
