package calendar.year._2024.day01;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class HistorianHysteriaTest extends BaseTest {

    private final HistorianHysteria exercise = initializeExercise(HistorianHysteria.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 2580760),
                Arguments.of(Part.PART_1, true, 11),
                Arguments.of(Part.PART_2, false, 25358365),
                Arguments.of(Part.PART_2, true, 31)
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
