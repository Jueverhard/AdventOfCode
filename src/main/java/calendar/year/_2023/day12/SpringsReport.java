package calendar.year._2023.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public record SpringsReport(String springStates, List<Integer> groupsSizes) {

    private static final Pattern CONTINUOUS_DAMAGED_PART = Pattern.compile("#+");


    public long countLegitArrangements() {
        List<String> statesGroups = new ArrayList<>(List.of(springStates.split("\\?")));
        int i = springStates.length() - 1;
        while (i >= 0 && SpringState.UNKNOWN.getLabel() == springStates.charAt(i)) {
            statesGroups.add("");
            i--;
        }

        return generateAlternativeStates(statesGroups).stream()
                .map(statesList -> new SpringsReport(statesList, groupsSizes))
                .filter(SpringsReport::isLegit)
                .count();
    }

    private boolean isLegit() {
        List<Integer> damagedPartsSizes = CONTINUOUS_DAMAGED_PART.matcher(springStates).results()
                .map(MatchResult::group)
                .map(String::length)
                .toList();

        return damagedPartsSizes.equals(groupsSizes);
    }

    private Set<String> generateAlternativeStates(List<String> states) {
        if (2 == states.size()) {
            return Set.of(
                    states.get(0) + SpringState.OPERATIONAL.getLabel() + states.get(1),
                    states.get(0) + SpringState.DAMAGED.getLabel() + states.get(1)
            );
        }

        return generateAlternativeStates(states.subList(1, states.size())).stream()
                .map(nextState -> Set.of(
                        states.get(0) + SpringState.OPERATIONAL.getLabel() + nextState,
                        states.get(0) + SpringState.DAMAGED.getLabel() + nextState
                ))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
