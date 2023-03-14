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
                Arguments.of(Part.PART_1, false),
                Arguments.of(Part.PART_1, true),
                Arguments.of(Part.PART_2, false),
                Arguments.of(Part.PART_2, true)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        long expectedResult;
        if (Part.PART_1.equals(part)) {
            expectedResult = testMode ? 10605L : 110264L;
        } else {
            expectedResult = testMode ? 2713310158L : 23612457316L;
        }
        assertEquals(expectedResult, res);
    }
}
