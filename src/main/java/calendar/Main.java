package calendar;

import calendar.year.enums.Part;
import utils.ExerciseFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        final int YEAR = 2020;
        final int DAY = 2;
        final Part exercisePart = Part.PART_2;
        final boolean testMode = true;
        LocalDate exerciseDate = LocalDate.of(YEAR, Month.DECEMBER, DAY);

        ExerciseFactory.getExercise(exerciseDate).run(exercisePart, testMode);
    }
}
