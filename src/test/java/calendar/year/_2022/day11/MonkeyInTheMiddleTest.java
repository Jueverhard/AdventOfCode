package calendar.year._2022.day11;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class MonkeyInTheMiddleTest extends BaseTest {

    private final MonkeyInTheMiddle exercise = initializeExercise(MonkeyInTheMiddle.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 110264),
                Arguments.of(Part.PART_1, true, 10605),
                Arguments.of(Part.PART_2, false, 23612457316L),
                Arguments.of(Part.PART_2, true, 2713310158L)
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
