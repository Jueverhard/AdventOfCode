package calendar.year._2023.day06;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.time.Instant;
import java.util.stream.Stream;

class WaitForItTest extends BaseTest {

    private final WaitForIt exercise = initializeExercise(WaitForIt.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 505494),
                Arguments.of(Part.PART_1, true, 288),
                Arguments.of(Part.PART_2, false, 23632299),
                Arguments.of(Part.PART_2, true, 71503)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode, Object expectedResult) throws IOException {
        // ARRANGE
        // ACT
        System.out.println(Instant.now());
        String res = exercise.run(part, testMode);
        System.out.println(Instant.now());

        // ASSERT
        assertEquals(expectedResult, res);
    }
}
