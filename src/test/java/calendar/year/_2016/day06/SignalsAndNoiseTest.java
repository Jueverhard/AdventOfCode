package calendar.year._2016.day06;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class SignalsAndNoiseTest extends BaseTest {
    private final SignalsAndNoise exercise = initializeExercise(SignalsAndNoise.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, "mshjnduc"),
                Arguments.of(Part.PART_1, true, "easter"),
                Arguments.of(Part.PART_2, false, "apfeeebz"),
                Arguments.of(Part.PART_2, true, "advent")
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
