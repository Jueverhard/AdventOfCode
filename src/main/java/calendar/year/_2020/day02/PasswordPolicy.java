package calendar.year._2020.day02;

import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PasswordPolicy {

    private int min;

    private int max;

    private char character;

    public PasswordPolicy(String inputLine) {
        String[] policyParts = inputLine.split(" ");
        String[] limits = policyParts[0].split("-");
        this.min = Integer.parseInt(limits[0]);
        this.max = Integer.parseInt(limits[1]);
        this.character = policyParts[1].charAt(0);
    }

    /**
     * @param password Password to try against the policy
     * @return true if the password respects the first exercise policy
     */
    public boolean validatesPasswordExercise1(String password) {
        // Counts the number of occurrences of the expected character
        Map<Character, Long> map = Arrays.stream(password.split(""))
                .map(string -> string.charAt(0))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        long characterOccurences = ObjectUtils.firstNonNull(map.get(character), 0L);

        return characterOccurences >= min && characterOccurences <= max;
    }

    /**
     * @param password Password to try against the policy
     * @return true if the password respects the second exercise policy
     */
    public boolean validatesPasswordExercise2(String password) {
        return password.charAt(min - 1) == character ^ password.charAt(max - 1) == character;
    }
}
