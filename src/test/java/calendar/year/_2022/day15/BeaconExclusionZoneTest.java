package calendar.year._2022.day15;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class BeaconExclusionZoneTest extends BaseTest {

    private final BeaconExclusionZone exercise = initializeExercise(BeaconExclusionZone.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false)//,
                // TODO JEV : second part hasn't been solved yet
                // Arguments.of(Part.PART_2, false),
                // Arguments.of(Part.PART_2, true)
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
            expectedResult = testMode ? 26 : 6078701;
        } else {
            // TODO JEV : real result isn't known yet, as the second part hasn't been solved
            expectedResult = testMode ? 93 : 42;
        }
        assertEquals(expectedResult, res);
    }
}
