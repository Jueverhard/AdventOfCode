package calendar.year._2021.day08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static calendar.year._2021.day08.Digit.UNIQUE_SEGMENT_SIZED_DIGIT;

public record Display(List<Signal> patterns, List<Signal> outputs) {

    /**
     * Computes the value of the current display based on scrambled segment mappings.
     * The method first decodes the scrambled signals into their original segments and then
     * converts the decoded signals into digits. Finally, these digits are combined to form
     * the resulting integer value.
     *
     * @return The integer value represented by the decoded segments.
     */
    public int computeValue() {
        // Identifies the scrambling
        Map<Character, Character> originalSegmentsPerMixedUp = computeMixedUpSegmentsMappingToOriginal();

        return outputs.stream()
                // Decodes the scrambled signals into their original segments
                .map(output -> output.transform(originalSegmentsPerMixedUp))
                // Identifies the corresponding digit
                .map(output -> Digit.fromSegments(output.segments()))
                .map(Digit::ordinal)
                // Builds the 4-digit number
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

        // Extracts the 1-segments
        Signal one = findSignalOfUniqueSegmentSizedDigit(Digit.ONE);
        Set<Character> possibleRightSegments = one.segments();

        // Extracts the 7-segments
        Signal seven = findSignalOfUniqueSegmentSizedDigit(Digit.SEVEN);

        // The only segment from 1 that is missing from 7 is the top one
        char topSegment = filteredSet(seven.segments(), one.segments()).iterator().next();
        mapping.put(topSegment, 'a');

        // Extracts the 4-segments
        Signal four = findSignalOfUniqueSegmentSizedDigit(Digit.FOUR);
        Set<Character> possibleTopLeftAndMiddleSegments = filteredSet(four.segments(), one.segments());

        // Extracts 2/3/5-segments
        Set<Signal> twoThreeFiveSignals = patterns.stream()
                .filter(pattern -> 5 == pattern.length())
                .collect(Collectors.toSet());

        // Finds the only common segment (the middle one) between 2/3/5 and 4
        char middleSegment = findCommonSegment(twoThreeFiveSignals, four.segments());
        mapping.put(middleSegment, 'd');
        mapping.put(findStillUnknownSegment(possibleTopLeftAndMiddleSegments, middleSegment), 'b');

        // Extracts 0/6/9-segments
        Set<Signal> zeroSixNineSignals = patterns.stream()
                .filter(pattern -> 6 == pattern.length())
                .collect(Collectors.toSet());

        // Finds the only common segment (the bottom-right one) between 0/6/9 and 1
        char bottomRightSegment = findCommonSegment(zeroSixNineSignals, one.segments());
        mapping.put(bottomRightSegment, 'f');
        mapping.put(findStillUnknownSegment(possibleRightSegments, bottomRightSegment), 'c');

        // Extracts the remaining unknown segments
        Set<Character> stillUnknownSegment = Digit.EIGHT.getSegments().stream()
                .filter(segment -> !mapping.containsKey(segment))
                .collect(Collectors.toSet());

        // Finds the only common segment (the bottom one) between 0/6/9 and the remaining unknown ones (bottom and bottom-left)
        char bottomSegment = findCommonSegment(zeroSixNineSignals, stillUnknownSegment);
        mapping.put(bottomSegment, 'g');
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
