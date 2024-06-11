package calendar.year._2017.day08;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IHeardYouLikeRegisters extends Exercise {
    public IHeardYouLikeRegisters(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Map<String, Integer> valuePerRegister = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern pattern = Pattern.compile("(?<register>\\w+) (?<operation>\\w+) (?<increment>-?\\d+) if (?<targetRegister>\\w+) (?<operator>.+) (?<comparedValue>-?\\d+)");
            while (null != (line = br.readLine())) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException();
                }

                String register = matcher.group("register");
                boolean isToInc = "inc".equals(matcher.group("operation"));
                int increment = Integer.parseInt(matcher.group("increment"));
                String targetRegister = matcher.group("targetRegister");
                LogicOperator operator = LogicOperator.fromValue(matcher.group("operator"));
                int comparedValue = Integer.parseInt(matcher.group("comparedValue"));

                if (!valuePerRegister.containsKey(register)) {
                    valuePerRegister.put(register, 0);
                }
                if (!valuePerRegister.containsKey(targetRegister)) {
                    valuePerRegister.put(targetRegister, 0);
                }

                if (operator.compute(valuePerRegister.get(targetRegister), comparedValue)) {
                    int newValue = isToInc ?
                            valuePerRegister.get(register) + increment :
                            valuePerRegister.get(register) - increment;
                    valuePerRegister.replace(register, newValue);
                }
            }
        }

        int result = Collections.max(valuePerRegister.values());
        return print(result);
    }
}
