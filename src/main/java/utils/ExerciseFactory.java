package utils;

import calendar.year._2015.day02.IWasToldThereWouldBeNoMath;
import calendar.year._2015.day03.PerfectlySphericalHousesVacuum;
import calendar.year._2015.day05.DoesntHeHaveInternElvesForThis;
import calendar.year._2015.day06.ProbablyAFireHazard;
import calendar.year._2016.day01.NoTimeForATaxicab;
import calendar.year._2016.day02.BathroomSecurity;
import calendar.year._2016.day04.SecurityThroughObscurity;
import calendar.year._2016.day06.SignalsAndNoise;
import calendar.year._2017.day01.InverseCaptcha;
import calendar.year._2017.day02.CorruptionChecksum;
import calendar.year._2017.day03.SpiralMemory;
import calendar.year._2017.day05.AMazeOfTwistyTrampolines;
import calendar.year._2017.day06.MemoryReallocation;
import calendar.year._2018.day01.ChronalCalibration;
import calendar.year._2018.day02.InventoryManagementSystem;
import calendar.year._2018.day03.NoMatterHowYouSliceIt;
import calendar.year._2018.day05.AlchemicalReduction;
import calendar.year._2018.day06.ChronalCoordinates;
import calendar.year._2019.day01.TheTyrannyOfTheRocketEquation;
import calendar.year._2019.day02.ProgramAlarm;
import calendar.year._2020.day01.ReportRepair;
import calendar.year._2020.day02.PasswordPhilosophy;
import calendar.year._2020.day05.BinaryBoarding;
import calendar.year._2021.day01.SonarSweep;
import calendar.year._2021.day02.Dive;
import calendar.year._2021.day03.BinaryDiagnostic;
import calendar.year._2021.day05.HydrothermalVenture;
import calendar.year._2021.day06.Lanternfish;
import calendar.year._2022.day01.CalorieCounting;
import calendar.year._2022.day02.RockPaperScissors;
import calendar.year._2022.day03.RucksackReorganization;
import calendar.year._2022.day04.CampCleanup;
import calendar.year._2022.day05.SupplyStacks;
import calendar.year._2022.day06.TuningTrouble;
import calendar.year._2022.day07.NoSpaceLeftOnDevice;
import calendar.year._2022.day08.TreetopTreeHouse;
import calendar.year._2022.day09.RopeBridge;
import calendar.year._2022.day10.CathodeRayTube;
import calendar.year._2022.day11.MonkeyInTheMiddle;
import calendar.year._2022.day12.HillClimbingAlgorithm;
import calendar.year._2022.day13.DistressSignal;
import calendar.year._2022.day14.RegolithReservoir;
import calendar.year._2022.day15.BeaconExclusionZone;
import calendar.year._2022.day16.ProboscideaVolcanium;
import calendar.year._2022.day17.PyroclasticFlow;
import calendar.year._2023.day01.Trebuchet;
import calendar.year._2023.day02.CubeConundrum;
import calendar.year._2023.day03.GearRatios;
import calendar.year._2023.day04.Scratchcards;
import calendar.year._2023.day05.IfYouGiveASeedAFertilizer;
import calendar.year._2023.day06.WaitForIt;
import calendar.year._2023.day07.CamelCards;
import calendar.year._2023.day08.HauntedWasteland;
import calendar.year._2023.day09.MirageMaintenance;
import calendar.year._2023.day11.CosmicExpansion;
import calendar.year._2023.day13.PointOfIncidence;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Map;

public class ExerciseFactory {

    private ExerciseFactory() {}

    private static final Map<LocalDate, Class<? extends Exercise>> exercises = Map.ofEntries(
            Map.entry(LocalDate.of(2015, 12, 2), IWasToldThereWouldBeNoMath.class),
            Map.entry(LocalDate.of(2015, 12, 3), PerfectlySphericalHousesVacuum.class),
            Map.entry(LocalDate.of(2015, 12, 5), DoesntHeHaveInternElvesForThis.class),
            Map.entry(LocalDate.of(2015, 12, 6), ProbablyAFireHazard.class),
            Map.entry(LocalDate.of(2016, 12, 1), NoTimeForATaxicab.class),
            Map.entry(LocalDate.of(2016, 12, 2), BathroomSecurity.class),
            Map.entry(LocalDate.of(2016, 12, 4), SecurityThroughObscurity.class),
            Map.entry(LocalDate.of(2016, 12, 6), SignalsAndNoise.class),
            Map.entry(LocalDate.of(2017, 12, 1), InverseCaptcha.class),
            Map.entry(LocalDate.of(2017, 12, 2), CorruptionChecksum.class),
            Map.entry(LocalDate.of(2017, 12, 3), SpiralMemory.class),
            Map.entry(LocalDate.of(2017, 12, 5), AMazeOfTwistyTrampolines.class),
            Map.entry(LocalDate.of(2017, 12, 6), MemoryReallocation.class),
            Map.entry(LocalDate.of(2018, 12, 1), ChronalCalibration.class),
            Map.entry(LocalDate.of(2018, 12, 2), InventoryManagementSystem.class),
            Map.entry(LocalDate.of(2018, 12, 3), NoMatterHowYouSliceIt.class),
            Map.entry(LocalDate.of(2018, 12, 5), AlchemicalReduction.class),
            Map.entry(LocalDate.of(2018, 12, 6), ChronalCoordinates.class),
            Map.entry(LocalDate.of(2019, 12, 1), TheTyrannyOfTheRocketEquation.class),
            Map.entry(LocalDate.of(2019, 12, 2), ProgramAlarm.class),
            Map.entry(LocalDate.of(2020, 12, 1), ReportRepair.class),
            Map.entry(LocalDate.of(2020, 12, 2), PasswordPhilosophy.class),
            Map.entry(LocalDate.of(2020, 12, 5), BinaryBoarding.class),
            Map.entry(LocalDate.of(2021, 12, 1), SonarSweep.class),
            Map.entry(LocalDate.of(2021, 12, 2), Dive.class),
            Map.entry(LocalDate.of(2021, 12, 3), BinaryDiagnostic.class),
            Map.entry(LocalDate.of(2021, 12, 5), HydrothermalVenture.class),
            Map.entry(LocalDate.of(2021, 12, 6), Lanternfish.class),
            Map.entry(LocalDate.of(2022, 12, 1), CalorieCounting.class),
            Map.entry(LocalDate.of(2022, 12, 2), RockPaperScissors.class),
            Map.entry(LocalDate.of(2022, 12, 3), RucksackReorganization.class),
            Map.entry(LocalDate.of(2022, 12, 4), CampCleanup.class),
            Map.entry(LocalDate.of(2022, 12, 5), SupplyStacks.class),
            Map.entry(LocalDate.of(2022, 12, 6), TuningTrouble.class),
            Map.entry(LocalDate.of(2022, 12, 7), NoSpaceLeftOnDevice.class),
            Map.entry(LocalDate.of(2022, 12, 8), TreetopTreeHouse.class),
            Map.entry(LocalDate.of(2022, 12, 9), RopeBridge.class),
            Map.entry(LocalDate.of(2022, 12, 10), CathodeRayTube.class),
            Map.entry(LocalDate.of(2022, 12, 11), MonkeyInTheMiddle.class),
            Map.entry(LocalDate.of(2022, 12, 12), HillClimbingAlgorithm.class),
            Map.entry(LocalDate.of(2022, 12, 13), DistressSignal.class),
            Map.entry(LocalDate.of(2022, 12, 14), RegolithReservoir.class),
            Map.entry(LocalDate.of(2022, 12, 15), BeaconExclusionZone.class),
            Map.entry(LocalDate.of(2022, 12, 16), ProboscideaVolcanium.class),
            Map.entry(LocalDate.of(2022, 12, 17), PyroclasticFlow.class),
            Map.entry(LocalDate.of(2023, 12, 1), Trebuchet.class),
            Map.entry(LocalDate.of(2023, 12, 2), CubeConundrum.class),
            Map.entry(LocalDate.of(2023, 12, 3), GearRatios.class),
            Map.entry(LocalDate.of(2023, 12, 4), Scratchcards.class),
            Map.entry(LocalDate.of(2023, 12, 5), IfYouGiveASeedAFertilizer.class),
            Map.entry(LocalDate.of(2023, 12, 6), WaitForIt.class),
            Map.entry(LocalDate.of(2023, 12, 7), CamelCards.class),
            Map.entry(LocalDate.of(2023, 12, 8), HauntedWasteland.class),
            Map.entry(LocalDate.of(2023, 12, 9), MirageMaintenance.class),
            Map.entry(LocalDate.of(2023, 12, 11), CosmicExpansion.class),
            Map.entry(LocalDate.of(2023, 12, 13), PointOfIncidence.class)
    );

    /**
     * @param date Date of an exercise
     * @return The exercise corresponding to the given date
     */
    public static Exercise getExercise(LocalDate date) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return exercises.get(date).getDeclaredConstructor(LocalDate.class).newInstance(date);
    }

    /**
     * @param clazz The exercise class
     * @return The exercise corresponding to the given class
     */
    public static <T extends Exercise> Exercise getExercise(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LocalDate date = exercises.entrySet().stream()
                .filter(exercise -> exercise.getValue().equals(clazz))
                .findFirst().orElseThrow()
                .getKey();
        return getExercise(date);
    }
}
