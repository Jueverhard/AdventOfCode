package calendar.year._2017.day01;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class InverseCaptcha extends Exercise {
    public InverseCaptcha(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String input = br.readLine();
            String[] digits = input.split("");

            return Part.PART_1.equals(part) ? exercise1(digits) : exercise2(digits);
        }
    }

    private String exercise1(String[] digits) {
        int score = 0;
        String previousDigit = digits[digits.length - 1];
        for (String digit : digits) {
            if (digit.equals(previousDigit)) {
                score += Integer.parseInt(digit);
            }
            previousDigit = digit;
        }

        return print(score);
    }

    private String exercise2(String[] digits) {
        int score = 0;
        for (int i = 0; i < digits.length; i++) {
            String digit = digits[i];
            String digitToCompare = digits[(i + digits.length / 2) % digits.length];
            if (digit.equals(digitToCompare)) {
                score += Integer.parseInt(digit);
            }
        }

        return print(score);
    }
}
