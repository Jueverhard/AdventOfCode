package utils;

import calendar.year._2016.day01.NoTimeForATaxicab;
import calendar.year._2016.day02.BathroomSecurity;
import calendar.year._2017.day01.InverseCaptcha;
import calendar.year._2017.day02.CorruptionChecksum;
import calendar.year._2018.day01.ChronalCalibration;
import calendar.year._2018.day02.InventoryManagementSystem;
import calendar.year._2019.day01.TheTyrannyOfTheRocketEquation;
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

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Map;

public class ExerciseFactory {

    private ExerciseFactory() {}

    private static final Map<LocalDate, Class<? extends Exercise>> exercises = Map.ofEntries(
            Map.entry(LocalDate.of(2016, 12, 1), NoTimeForATaxicab.class),
            Map.entry(LocalDate.of(2016, 12, 2), BathroomSecurity.class),
            Map.entry(LocalDate.of(2017, 12, 1), InverseCaptcha.class),
            Map.entry(LocalDate.of(2017, 12, 2), CorruptionChecksum.class),
            Map.entry(LocalDate.of(2018, 12, 1), ChronalCalibration.class),
            Map.entry(LocalDate.of(2018, 12, 2), InventoryManagementSystem.class),
            Map.entry(LocalDate.of(2019, 12, 1), TheTyrannyOfTheRocketEquation.class),
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
            Map.entry(LocalDate.of(2022, 12, 17), PyroclasticFlow.class)
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
