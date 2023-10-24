package utils;

import lombok.AllArgsConstructor;
import utils.enums.Part;

import java.io.IOException;
import java.time.LocalDate;

@AllArgsConstructor
public abstract class Exercise {
    protected LocalDate date;

    public abstract String run(Part part, boolean testMode) throws IOException;

    protected String getInputPath(boolean testMode) {
        return String.format(
                "src/main/resources/%s/%d/day_%02d.txt",
                testMode ? "inputs/test" : "inputs",
                date.getYear(),
                date.getDayOfMonth()
        );
    }

    protected String print(Object object) {
        System.out.println(object);
        return object.toString();
    }
}
