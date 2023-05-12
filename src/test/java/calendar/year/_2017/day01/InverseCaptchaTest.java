package calendar.year._2017.day01;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class InverseCaptchaTest extends BaseTest {
    private final InverseCaptcha exercise = initializeExercise(InverseCaptcha.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 1049),
                Arguments.of(Part.PART_1, true, 9),
                Arguments.of(Part.PART_2, false, 1508),
                Arguments.of(Part.PART_2, true, 6)
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
