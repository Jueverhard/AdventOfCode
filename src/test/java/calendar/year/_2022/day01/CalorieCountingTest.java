package calendar.year._2022.day01;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class CalorieCountingTest extends BaseTest {

    private final CalorieCounting exercise = initializeExercise(CalorieCounting.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 69281),
                Arguments.of(Part.PART_1, true, 24000),
                Arguments.of(Part.PART_2, false, 201524),
                Arguments.of(Part.PART_2, true, 41000)
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
