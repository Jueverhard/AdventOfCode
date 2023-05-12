package calendar.year._2021.day02;

import calendar.BaseTest;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class DiveTest extends BaseTest {
    private final Dive exercise = initializeExercise(Dive.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 2070300),
                Arguments.of(Part.PART_1, true, 150),
                Arguments.of(Part.PART_2, false, new NotImplementedException()),
                Arguments.of(Part.PART_2, true, 900)
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
