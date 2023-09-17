package calendar.year._2021.day05;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class HydrothermalVentureTest extends BaseTest {
    private final HydrothermalVenture exercise = initializeExercise(HydrothermalVenture.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 6007),
                Arguments.of(Part.PART_1, true, 5),
                Arguments.of(Part.PART_2, false, 19349),
                Arguments.of(Part.PART_2, true, 12)
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
