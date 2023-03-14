package calendar.year._2020.day02;

import calendar.year.enums.Part;
import utils.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.function.BiPredicate;

public class PasswordPhilosophy extends Exercise {
    public PasswordPhilosophy(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        int nbMatchingPasswords = 0;
        // Defines the matching policy predicate
        BiPredicate<PasswordPolicy, String> matchingFunction = Part.PART_1.equals(part) ?
                PasswordPolicy::validatesPasswordExercise1 : PasswordPolicy::validatesPasswordExercise2;

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                // Extracts the password policy and the password to be tested against it
                String[] inputParts = line.split(": ");
                PasswordPolicy passwordPolicy = new PasswordPolicy(inputParts[0]);
                String password = inputParts[1];

                // Checks whether the password matches the policy
                if (matchingFunction.test(passwordPolicy, password)) {
                    nbMatchingPasswords++;
                }
            }

        }

        return print(nbMatchingPasswords);
    }
}
