package calendar.year._2022.day16;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Valve {

    private static final Pattern CAPITAL_PATTERN = Pattern.compile("[A-Z]{2}");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private String id;

    private int flowRate;

    private Set<String> reachableValveIds = new HashSet<>();

    public Valve(String inputLine) {
        Matcher valveIdMatcher = CAPITAL_PATTERN.matcher(inputLine);
        Matcher flowRateMatcher = NUMBER_PATTERN.matcher(inputLine);
        if (!valveIdMatcher.find()) {
            throw new IllegalArgumentException("L'input fourni ne contient pas de valve :\n" + inputLine);
        }
        this.id = valveIdMatcher.group();

        if (!flowRateMatcher.find()) {
            throw new IllegalArgumentException("L'input fourni ne contient pas d'information sur son débit :\n" + inputLine);
        }
        this.flowRate = Integer.parseInt(flowRateMatcher.group());

        while (valveIdMatcher.find()) {
            this.reachableValveIds.add(valveIdMatcher.group());
        }
    }
}
