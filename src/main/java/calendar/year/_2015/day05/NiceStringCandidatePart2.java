package calendar.year._2015.day05;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NiceStringCandidatePart2 extends NiceStringCandidate {

    public NiceStringCandidatePart2(String candidate) {
        super(candidate);
    }

    @Override
    public boolean isNice() {
        return containsAtLeastOneRepeatedPair() && containsAtLeastTwoIdenticalLettersSpacedByOnlyOneCharacter();
    }

    private boolean containsAtLeastOneRepeatedPair() {
        // On compte le nombre d'occurrences de chaque sous-string de 2 caractères
        Map<String, Long> nbOccurencesOfCharacterPairs = IntStream.range(0, this.candidate.length() - 1)
                .mapToObj(index -> this.candidate.substring(index, index + 2))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        return nbOccurencesOfCharacterPairs.entrySet().stream()
                // On ne conserve que les sous-strings qui se répètent
                .filter(entry -> entry.getValue() > 1)
                .map(Entry::getKey)
                // On vérifie qu'au moins deux répétitions ne se chevauchent
                .anyMatch(string -> this.candidate.lastIndexOf(string) - this.candidate.indexOf(string) > 1);
    }

    private boolean containsAtLeastTwoIdenticalLettersSpacedByOnlyOneCharacter() {
        return IntStream.range(0, this.candidate.length() - 2)
                .mapToObj(index -> this.candidate.substring(index, index + 3))
                .anyMatch(string -> string.charAt(0) == string.charAt(2));
    }
}
