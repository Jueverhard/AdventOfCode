package calendar.year._2021.day08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static calendar.year._2021.day08.Digit.UNIQUE_SEGMENT_SIZED_DIGIT;

public record Display(List<Signal> patterns, List<Signal> outputs) {

    private static final Set<Character> EXISTING_SEGMENTS = Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g');

    public int computeValue() {
        Map<Character, Character> originalSegmentsPerMixedUp = computeMixedUpSegmentsMappingToOriginal();

        return outputs.stream()
                .map(output -> output.transform(originalSegmentsPerMixedUp))
                .map(output -> Digit.fromSegments(output.segments()))
                .map(Digit::ordinal)
                .reduce(0, (acc, val) -> 10 * acc + val);
    }

    /**
     * Computes the mapping between the mixed up segments and the original segments.
     * The computed mapping can be used to decode signals that have their segments scrambled.
     *
     * @return A map where the keys are mixed up segments and the values are the corresponding original segments.
     */
    private Map<Character, Character> computeMixedUpSegmentsMappingToOriginal() {
        Map<Character, Character> mapping = new HashMap<>();

        // ab = 1
        // ==> `a` correspond à `c` ou `f`
        // ==> `b` correspond à `c` ou `f`
        Signal one = findSignalOfUniqueSegmentSizedDigit(Digit.ONE);
        Set<Character> possibleRightSegments = one.segments();

        // dab = 7
        // ==> `d` correspond à `a`  (d ==> a)
        //  dddd
        // ?    ?
        // ?    ?
        //  ????
        // ?    ?
        // ?    ?
        //  ????
        Signal seven = findSignalOfUniqueSegmentSizedDigit(Digit.SEVEN);
        char topSegment = filteredSet(seven.segments(), one.segments()).iterator().next();
        mapping.put(topSegment, 'a');

        // eafb = 4
        // ==> `e` correspond à `b` ou `d`
        // ==> `f` correspond à `b` ou `d`
        Signal four = findSignalOfUniqueSegmentSizedDigit(Digit.FOUR);
        Set<Character> possibleTopLeftAndMiddleSegments = filteredSet(four.segments(), one.segments());

        // 2, 3, 5 ont tous un unique segment commun avec 4 : celui du centre
        // cdfbe gcdfa fbcad ont tous un unique segment commun avec eafb : f
        // ==> `f` correspond à `d`  (d ==> a, f ==> d)
        // ==> `e` correspond à `b`  (d ==> a, f ==> d, e ==> b)
        //  dddd
        // e    ?
        // e    ?
        //  ffff
        // ?    ?
        // ?    ?
        //  ????
        Set<Signal> twoThreeFiveSignals = patterns.stream()
                .filter(pattern -> 5 == pattern.length())
                .collect(Collectors.toSet());
        char middleSegment = findCommonSegment(twoThreeFiveSignals, four.segments());
        mapping.put(middleSegment, 'd');
        mapping.put(findStillUnknownSegment(possibleTopLeftAndMiddleSegments, middleSegment), 'b');

        // 0, 6, 9 ont tous un unique segment commun avec 1 : celui en bas à droite
        // cefabd cdfgeb cagedb ont tous un unique segment commun avec ab : b
        // ==> `b` correspond à `f`  (d ==> a, f ==> d, e ==> b, b ==> f)
        // ==> `a` correspond à `c`  (d ==> a, f ==> d, e ==> b, b ==> f, a ==> c)
        //  dddd
        // e    a
        // e    a
        //  ffff
        // ?    b
        // ?    b
        //  ????
        Set<Signal> zeroSixNineSignals = patterns.stream()
                .filter(pattern -> 6 == pattern.length())
                .collect(Collectors.toSet());
        char bottomRightSegment = findCommonSegment(zeroSixNineSignals, one.segments());
        mapping.put(bottomRightSegment, 'f');
        mapping.put(findStillUnknownSegment(possibleRightSegments, bottomRightSegment), 'c');

        // Dans les inconnues restantes (eg),
        // 0, 6, 9 n'ont qu'un segment commun avec eg : celui en bas
        // cefabd cdfgeb cagedb n'ont qu'un segment commun avec eg : e
        // ==> `e` correspond à `g`
        //  dddd
        // e    a
        // e    a
        //  ffff
        // ?    b
        // ?    b
        //  cccc
        Set<Character> stillUnknownSegment = EXISTING_SEGMENTS.stream()
                .filter(segment -> !mapping.containsKey(segment))
                .collect(Collectors.toSet());
        char bottomSegment = findCommonSegment(zeroSixNineSignals, stillUnknownSegment);
        mapping.put(bottomSegment, 'g');

        // ==> `g` correspond à `c`
        //  dddd
        // e    a
        // e    a
        //  ffff
        // g    b
        // g    b
        //  cccc
        mapping.put(findStillUnknownSegment(stillUnknownSegment, bottomSegment), 'e');

        return mapping;
    }

    /**
     * Finds and returns the signal that matches the unique segment size of the given digit.
     * The digit must be one of the digits with a unique segment size.
     *
     * @param digit The digit for which the matching signal is to be found.
     * @return The signal corresponding to the given digit based on segment size.
     * @throws IllegalArgumentException If the digit does not have a unique segment size.
     */
    private Signal findSignalOfUniqueSegmentSizedDigit(Digit digit) {
        if (!UNIQUE_SEGMENT_SIZED_DIGIT.contains(digit)) {
            throw new IllegalArgumentException();
        }

        return patterns.stream()
                .filter(signal -> digit.getNbSegments() == signal.length())
                .findAny().orElseThrow();
    }

    /**
     * Filters out specified values from a given set.
     *
     * @param setToFilter    The set from which values need to be filtered out.
     * @param valuesToRemove The set containing values that should be removed.
     * @return A new set containing only the values that weren't to filter.
     */
    private <T> Set<T> filteredSet(Set<T> setToFilter, Set<T> valuesToRemove) {
        return setToFilter.stream()
                .filter(value -> !valuesToRemove.contains(value))
                .collect(Collectors.toSet());
    }

    /**
     * Finds the still unknown segment character by filtering out an already identified segment from the set of possible segments.
     *
     * @param possibleSegments  The set of characters representing the possible segments.
     * @param identifiedSegment The character of the segment that has already been identified.
     * @return The character representing the segment that remains unknown after filtering out the identified segment.
     */
    private char findStillUnknownSegment(Set<Character> possibleSegments, char identifiedSegment) {
        return filteredSet(possibleSegments, Set.of(identifiedSegment)).iterator().next();
    }

    /**
     * Identifies the common segment character from a set of signals based on their segments.
     *
     * @param unknownSignals  The set of signals where each signal consists of a set of segments.
     * @param unknownSegments The set of potential segments to check for commonality within the given signals.
     * @return The character representing the common segment shared among all provided signals.
     */
    private char findCommonSegment(Set<Signal> unknownSignals, Set<Character> unknownSegments) {
        Set<Character> commonChars = new HashSet<>(unknownSegments);
        // Remove all the not fully shared characters
        unknownSignals
                .forEach(unknownSignal -> commonChars.retainAll(unknownSignal.segments()));

        return commonChars.iterator().next();
    }
}
