package calendar.year._2016.day04;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Room {

    private String encryptedName;

    @Getter
    private int sectorId;

    private String checksum;

    public boolean isReal() {
        // Listing des caractères présents
        List<Character> characters = Pattern.compile("-").matcher(encryptedName).replaceAll("").chars()
                .mapToObj(c -> (char) c)
                .toList();

        // Listing des caractères présents par ordre de fréquence croissant
        List<Set<Character>> charactersPerFrequency = characters.stream()
                // Regroupement des caractères selon leur fréquence
                .collect(Collectors.groupingBy(
                        character -> characters.stream().filter(c -> c.equals(character)).count(),
                        Collectors.mapping(
                                character -> character,
                                Collectors.toSet()
                        )
                )).entrySet().stream()
                // Tri des caractères par fréquence
                .sorted(Comparator.comparingLong(Entry::getKey))
                .map(Entry::getValue)
                .collect(Collectors.toList());

        // Tri des caractères par ordre de fréquence décroissant
        Collections.reverse(charactersPerFrequency);

        // Extraction des 5 caractères les plus fréquents ()
        Iterator<Set<Character>> charsIterator = charactersPerFrequency.iterator();
        List<String> expectedChecksumChars = new ArrayList<>();
        while (expectedChecksumChars.size() < 5) {
            Iterator<Character> orderedCharsIterator = charsIterator.next().stream()
                    .sorted()
                    .iterator();

            List<String> frequencyTmpList = new ArrayList<>();
            while (orderedCharsIterator.hasNext() && expectedChecksumChars.size() + frequencyTmpList.size() < 5) {
                frequencyTmpList.add(String.valueOf(orderedCharsIterator.next()));
            }
            Collections.sort(frequencyTmpList);
            expectedChecksumChars.addAll(frequencyTmpList);
        }

        String expectedChecksum = String.join("", expectedChecksumChars);
        return checksum.equals(expectedChecksum);
    }

    public String getDecryptedName() {
        StringBuilder decryptedName = new StringBuilder();
        for (char encryptedChar : encryptedName.toCharArray()) {
            if ('-' == encryptedChar) {
                decryptedName.append(' ');
            } else {
                int encryptedIndex = encryptedChar - 'a';
                int decryptedIndex = (encryptedIndex + sectorId) % 26;
                char decryptedChar = (char) ('a' + decryptedIndex);
                decryptedName.append(decryptedChar);
            }
        }
        return decryptedName.toString();
    }
}
