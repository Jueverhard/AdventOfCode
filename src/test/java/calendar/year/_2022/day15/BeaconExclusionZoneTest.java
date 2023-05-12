package calendar.year._2022.day15;

import calendar.BaseTest;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Disabled;
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
                Arguments.of(Part.PART_1, true, 26),
                Arguments.of(Part.PART_2, false, 6078701),
                // TODO JEV : second part hasn't been solved yet
                Arguments.of(Part.PART_2, true, 93),
                Arguments.of(Part.PART_2, false, new NotImplementedException())
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    @Disabled("FIXME : Part 1 is broken + takes too much time")
    void run_test(Part part, boolean testMode, Object expectedResult) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        assertEquals(expectedResult, res);
    }
}
