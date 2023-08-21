package calendar.year._2018.day05;

import lombok.Getter;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Polymer(@Getter String chain) {

    private static final Set<String> POSSIBLE_REACTIONS = IntStream.range(65, 91)
            .mapToObj(intChar -> Set.of(
                    String.valueOf((char) intChar).toLowerCase() + (char) intChar,
                    (char) intChar + String.valueOf((char) intChar).toLowerCase()
            ))
            .flatMap(Set::stream)
            .collect(Collectors.toUnmodifiableSet());

    /**
     * Compute all reactions that may happen inside the polymer chain
     *
     * @return the resulting polymer chain
     */
    public String computeFullyReactedChain() {
        String reactedChain = this.chain;
        Optional<String> optReaction;

        do {
            optReaction = searchPossibleReaction(reactedChain);
            if (optReaction.isPresent()) {
                reactedChain = reactedChain.replace(optReaction.get(), "");
            }
        } while (optReaction.isPresent());

        return reactedChain;
    }

    /**
     * @param chain Polymer chain to analyse
     * @return any possible reaction contained
     */
    private Optional<String> searchPossibleReaction(String chain) {
        return POSSIBLE_REACTIONS.stream()
                .filter(chain::contains)
                .findFirst();
    }

    /**
     * Computes all possible final polymer chains when removing one letter from the original chain
     *
     * @return The shortest polymer chain
     */
    public String computeShortestFullyReactedChain() {
        // Génération des 26 lettres de l'alphabet
        return IntStream.range(65, 91)
                .mapToObj(intChar -> (char) intChar)
                // Génération des 26 variantes de la chaîne privées d'un caractère
                .map(letter -> this.chain.replaceAll("(?i)" + letter, ""))
                // Création du polymère associé
                .map(Polymer::new)
                // Calcul des polymères finaux (une fois toutes les réactions faites)
                .map(Polymer::computeFullyReactedChain)
                // Récupération du polymère final le plus court
                .min(Comparator.comparingInt(String::length))
                .orElseThrow();
    }
}
