package calendar.year._2022.day02;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class RockPaperScissors extends Exercise {

    public RockPaperScissors(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            int score;
            if (Part.PART_1.equals(part)) {
                score = exercise1(br);
            } else {
                score = exercise2(br);
            }

            return print(score);
        }
    }

    private int exercise1(BufferedReader br) throws IOException {
        final Map<Character, Integer> pointsPerSign = Map.of(
                'X', 1,
                'Y', 2,
                'Z', 3
        );
        final Map<String, Integer> pointsPerBattle = Map.of(
                "AX", 3,
                "AY", 6,
                "AZ", 0,
                "BX", 0,
                "BY", 3,
                "BZ", 6,
                "CX", 6,
                "CY", 0,
                "CZ", 3
        );

        int score = 0;
        String line;
        while ((line = br.readLine()) != null) {
            char opponent = line.charAt(0);
            char self = line.charAt(2);
            score += pointsPerSign.get(self);
            score += pointsPerBattle.get("" + opponent + self);
        }

        return score;
    }

    private int exercise2(BufferedReader br) throws IOException {
        final Map<Character, Integer> pointsPerSign = Map.of(
                'A', 1,
                'B', 2,
                'C', 3
        );
        final Map<Character, Integer> pointsPerBattleIssue = Map.of(
                'X', 0,
                'Y', 3,
                'Z', 6
        );
        final Map<String, Character> strategy = Map.of(
                "AX", 'C',
                "AY", 'A',
                "AZ", 'B',
                "BX", 'A',
                "BY", 'B',
                "BZ", 'C',
                "CX", 'B',
                "CY", 'C',
                "CZ", 'A'
        );

        int score = 0;
        String line;
        while ((line = br.readLine()) != null) {
            char opponent = line.charAt(0);
            char issue = line.charAt(2);
            score += pointsPerBattleIssue.get(issue);

            char signToDo = strategy.get("" + opponent + issue);
            score += pointsPerSign.get(signToDo);
        }

        return score;
    }
}
