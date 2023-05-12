package calendar.year._2019.day01;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class TheTyrannyOfTheRocketEquationTest extends BaseTest {
    private final TheTyrannyOfTheRocketEquation exercise = initializeExercise(TheTyrannyOfTheRocketEquation.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 3286680),
                Arguments.of(Part.PART_1, true, 34241),
                Arguments.of(Part.PART_2, false, 4927158),
                Arguments.of(Part.PART_2, true, 51312)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode, Object expectedResult) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        assertEquals(expectedResult, res);
    }

}
