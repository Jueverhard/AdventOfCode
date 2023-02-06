package calendar.year._2022.day03;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RucksackReorganization extends Exercise {

    public RucksackReorganization(LocalDate date) {
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
        String line;
        int score = 0;

        while ((line = br.readLine()) != null) {
            String left = line.substring(0, line.length() / 2);
            String right = line.substring(line.length() / 2);
            char doublon = findDuplicate(left, right);
            score += getValue(doublon);
        }

        return score;
    }

    private int exercise2(BufferedReader br) throws IOException {
        String line;
        List<String> currentTeam = new ArrayList<>();
        Map<Character, List<Integer>> countPerChar = new HashMap<>();
        int score = 0;
        while ((line = br.readLine()) != null) {
            int index = currentTeam.size();
            for (char c : line.toCharArray()) {
                countPerChar.computeIfAbsent(c, k -> new ArrayList<>(List.of(0, 0, 0)));
                List<Integer> actualCountList = countPerChar.get(c);
                int actualCount = actualCountList.get(index);
                actualCountList.set(index, actualCount + 1);
            }
            currentTeam.add(line);
            if (currentTeam.size() == 3) {
                char teamBadge = countPerChar.entrySet().stream()
                        .filter(entry -> entry.getValue().stream().allMatch(count -> count > 0))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())
                        .get(0);
                score += getValue(teamBadge);
                currentTeam.clear();
                countPerChar.clear();
            }
        }

        return score;
    }

    private char findDuplicate(String left, String right) {
        final List<String> rightChars = List.of(right.split(""));
        for (String s : left.split("")) {
            if (rightChars.contains(s)) {
                return s.toCharArray()[0];
            }
        }
        return '0';
    }

    private int getValue(char c) {
        int res = c == Character.toUpperCase(c) ? 26 : 0;
        res += Character.getNumericValue(c) - 9;
        return res;
    }
}
