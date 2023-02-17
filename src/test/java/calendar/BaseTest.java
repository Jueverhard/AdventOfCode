package calendar;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import utils.Exercise;
import utils.ExerciseFactory;

public abstract class BaseTest {

    protected void assertEquals(int expectedResult, String actualResult) {
        Assertions.assertEquals(String.valueOf(expectedResult), actualResult);
    }

    protected void assertEquals(long expectedResult, String actualResult) {
        Assertions.assertEquals(String.valueOf(expectedResult), actualResult);
    }

    protected void assertEquals(String expectedResult, String actualResult) {
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    protected <T extends Exercise> T initializeExercise(Class<T> exerciseClass) {
        return (T) ExerciseFactory.getExercise(exerciseClass);
    }
}
