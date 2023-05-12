package calendar.year._2020.day01;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class ReportRepairTest extends BaseTest {
    private final ReportRepair exercise = initializeExercise(ReportRepair.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 889779),
                Arguments.of(Part.PART_1, true, 514579),
                Arguments.of(Part.PART_2, false, 76110336),
                Arguments.of(Part.PART_2, true, 241861950)
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
