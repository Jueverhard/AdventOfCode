package calendar.year._2018.day02;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class InventoryManagementSystemTest extends BaseTest {
    private final InventoryManagementSystem exercise = initializeExercise(InventoryManagementSystem.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, "7776"),
                Arguments.of(Part.PART_1, true, "0"),
                Arguments.of(Part.PART_2, false, "wlkigsqyfecjqqmnxaktdrhbz"),
                Arguments.of(Part.PART_2, true, "fgij")
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
