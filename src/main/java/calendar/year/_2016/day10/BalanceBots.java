package calendar.year._2016.day10;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BalanceBots extends Exercise {

    public BalanceBots(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        Bot.setUpLookedUpValues(testMode ? List.of(2, 5) : List.of(17, 61));
        List<Bot> bots = new ArrayList<>();

//        record ParsedBot(int id, int lowTargetId, String lowTargetNature, int highTargetId, String highTargetNature) {}
        record BotValuation(int botId, int value) {}
//        List<ParsedBot> parsedBots = new ArrayList<>();
        List<BotValuation> botValuations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            final Pattern botReceivingPattern = Pattern.compile("value (?<value>\\d+) goes to bot (?<botId>\\d+)");
            final Pattern botGivingPattern = Pattern.compile("bot (?<givingBotId>\\d+) gives low to (?<lowTargetNature>bot|output) (?<lowTargetId>\\d+) and high to (?<highTargetNature>bot|output) (?<highTargetId>\\d+)");
            while (null != (line = br.readLine())) {
                Matcher botReceivingMatch = botReceivingPattern.matcher(line);
                Matcher botGivingMatch = botGivingPattern.matcher(line);
                if (botReceivingMatch.matches()) {
                    botValuations.add(new BotValuation(
                            Integer.parseInt(botReceivingMatch.group("botId")),
                            Integer.parseInt(botReceivingMatch.group("value"))
                    ));
                } else if (botGivingMatch.matches()) {
                    bots.add(new Bot(
                            Integer.parseInt(botGivingMatch.group("givingBotId")),
                            Integer.parseInt(botGivingMatch.group("lowTargetId")),
                            Integer.parseInt(botGivingMatch.group("highTargetId"))
                    ));
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
        Map<Integer, Bot> botById = bots.stream()
                .collect(Collectors.toMap(Bot::getId, bot -> bot));
        bots.forEach(bot -> bot.initialize(botById));

        // Runs bots
        Optional<Integer> optFoundBotId = Optional.empty();
        Iterator<BotValuation> botValuationsIterator = botValuations.iterator();
        while (optFoundBotId.isEmpty() && botValuationsIterator.hasNext()) {
            BotValuation botValuation = botValuationsIterator.next();
            Bot bot = botById.get(botValuation.botId());

            optFoundBotId = bot.addValue(botValuation.value());
        }

        return optFoundBotId
                .map(this::print)
                .orElseThrow();
    }
}
