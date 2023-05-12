package calendar;

import lombok.SneakyThrows;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Assertions;
import utils.Exercise;
import utils.ExerciseFactory;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class BaseTest {

    protected void assertEquals(Object expected, String actual) {
        if (expected instanceof String) {
            assertEquals((String) expected, actual);
        } else if (expected instanceof Long) {
            assertEquals((long) expected, actual);
        } else if (expected instanceof Integer) {
            assertEquals((int) expected, actual);
        } else if (expected instanceof NotImplementedException) {
            fail("Not yet implemented");
        } else {
            throw new UnsupportedOperationException("Le type de la valeur attendue n'est pas prévu : " + expected.getClass());
        }
    }

    private void assertEquals(int expectedResult, String actualResult) {
        Assertions.assertEquals(String.valueOf(expectedResult), actualResult);
    }

    private void assertEquals(long expectedResult, String actualResult) {
        Assertions.assertEquals(String.valueOf(expectedResult), actualResult);
    }

    private void assertEquals(String expectedResult, String actualResult) {
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    protected <T extends Exercise> T initializeExercise(Class<T> exerciseClass) {
        return (T) ExerciseFactory.getExercise(exerciseClass);
    }
}
