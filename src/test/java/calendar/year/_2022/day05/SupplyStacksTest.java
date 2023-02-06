package calendar.year._2022.day05;

import calendar.BaseTest;
import calendar.year.enums.Part;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class SupplyStacksTest extends BaseTest {

    private final SupplyStacks exercise = initializeExercise(SupplyStacks.class);

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
        String expectedResult;
        if (Part.PART_1.equals(part)) {
            expectedResult = testMode ? "CMZ" : "TLNGFGMFN";
        } else {
            expectedResult = testMode ? "MCD" : "FGLQJCMBD";
        }
        Assertions.assertEquals(expectedResult, res);
    }
}
