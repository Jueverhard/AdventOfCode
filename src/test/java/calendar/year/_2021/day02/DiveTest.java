package calendar.year._2021.day02;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

class DiveTest extends BaseTest {
    private final Dive exercise = initializeExercise(Dive.class);

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
        int expectedResult;
        if (Part.PART_1.equals(part)) {
            expectedResult = testMode ? 150 : 2070300;
        } else {
            fail("Not yet implemented");
            expectedResult = testMode ? 900 : -1;
        }
        assertEquals(expectedResult, res);
    }

}