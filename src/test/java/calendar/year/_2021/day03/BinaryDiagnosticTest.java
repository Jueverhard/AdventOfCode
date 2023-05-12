package calendar.year._2021.day03;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class BinaryDiagnosticTest extends BaseTest {
    private final BinaryDiagnostic exercise = initializeExercise(BinaryDiagnostic.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 738234),
                Arguments.of(Part.PART_1, true, 198),
                Arguments.of(Part.PART_2, false, 3969126),
                Arguments.of(Part.PART_2, true, 230)
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
