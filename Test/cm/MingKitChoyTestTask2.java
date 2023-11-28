package cm;

import cm.CarParkKind;
import cm.Period;
import cm.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MingKitChoyTestTask2 {
    private Rate rate;
    private ArrayList<Period> normalPeriods;
    private ArrayList<Period> reducedPeriods;
    private ArrayList<Period> overlappingNormalPeriods;
    private ArrayList<Period> overlappingReducedPeriods;

    @BeforeEach
    public void setUp() {
        // Common setup for all test cases
        normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));

        reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 10));

        overlappingNormalPeriods = new ArrayList<>();
        overlappingNormalPeriods.add(new Period(2, 5));
        overlappingNormalPeriods.add(new Period(4, 7));

        overlappingReducedPeriods = new ArrayList<>();
        overlappingReducedPeriods.add(new Period(7, 10));
        overlappingReducedPeriods.add(new Period(9, 12));

        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal("5"), new BigDecimal("2"), normalPeriods, reducedPeriods);
    }

    // Valid Input Test Cases (4)
    @Test
    public void test_001() {
        Period period = new Period(2, 5);
        assertEquals(3, period.duration());
    }

    @Test
    public void test_002() {
        rate = new Rate(CarParkKind.VISITOR, new BigDecimal("5"), new BigDecimal("2"), normalPeriods, reducedPeriods);
        assertNotNull(rate);
    }

    @Test
    public void test_003() {
        Period periodStay = new Period(3, 4);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("5"), charge);
    }

    @Test
    public void test_004() {
        Period periodStay = new Period(8, 9);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("2"), charge);
    }

    // Invalid Input Test Cases (4)
    @Test
    public void test_005() {
        assertThrows(IllegalArgumentException.class, () -> new Period(-1, 5));
    }

    @Test
    public void test_006() {
        assertThrows(IllegalArgumentException.class, () -> new Period(2, 25));
    }

    @Test
    public void test_007() {
        assertThrows(IllegalArgumentException.class, () -> new Period(8, 4));
    }

    @Test
    public void test_008() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, new BigDecimal("-5"), new BigDecimal("2"), normalPeriods, reducedPeriods));
    }

    // Valid Output Test Cases (4)
    @Test
    public void test_009() {
        Period periodStay = new Period(12, 15);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(BigDecimal.ZERO, charge);
    }

    @Test
    public void test_010() {
        Period periodStay = new Period(4, 8);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("7"), charge);
    }

    @Test
    public void test_011() {
        Period periodStay = new Period(3, 4);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("5"), charge);
    }

    @Test
    public void test_012() {
        Period periodStay = new Period(8, 9);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("2"), charge);
    }

    // Invalid Output Test Cases (4)
    @Test
    public void test_013() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, new BigDecimal("5"), new BigDecimal("2"), normalPeriods, overlappingReducedPeriods));
    }

    @Test
    public void test_014() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, new BigDecimal("5"), new BigDecimal("2"), overlappingNormalPeriods, reducedPeriods));
    }

    @Test
    public void test_015() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, new BigDecimal("5"), new BigDecimal("2"), overlappingNormalPeriods, overlappingReducedPeriods));
    }

    @Test
    public void test_016() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, new BigDecimal("-5"), new BigDecimal("-2"), normalPeriods, reducedPeriods));
    }
    //Task 2
    //Rate
    @Test
    public void RateTestCase1(){
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(5.25);
            BigDecimal reducedRate = BigDecimal.valueOf(7.25);
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            normalPeriods.add(new Period(2, 5));
            reducedPeriods.add(new Period(7, 10));

            new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }
    @Test
    public void RateTestCase2(){
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal reducedRate = BigDecimal.valueOf(0.25);
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            normalPeriods.add(new Period(2, 5));
            reducedPeriods.add(new Period(7, 10));

            new Rate(kind, null, reducedRate, normalPeriods, reducedPeriods);
        });
    }
    @Test
    public void RateTestCase3(){
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(0.25);
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            normalPeriods.add(new Period(2, 5));
            reducedPeriods.add(new Period(7, 10));

            new Rate(kind, normalRate, null, normalPeriods, reducedPeriods);
        });
    }
    @Test
    public void RateTestCase4(){
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(5);
            BigDecimal reducedRate = BigDecimal.valueOf(7);
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            //normalPeriods.add(new Period(0, 5));
            reducedPeriods.add(new Period(7, 10));

            new Rate(kind, normalRate, reducedRate, null, reducedPeriods);
        });
    }
    @Test
    public void RateTestCase5(){
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STUDENT;
            BigDecimal normalRate = BigDecimal.valueOf(5);
            BigDecimal reducedRate = BigDecimal.valueOf(7);
            ArrayList<Period> normalPeriods = new ArrayList<>();

            normalPeriods.add(new Period(0, 5));
            //reducedPeriods.add(new Period(7, 10));

            new Rate(kind, normalRate, reducedRate, normalPeriods, null);
        });
    }
    @Test
    public void RateTestCase6(){
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(1);
            BigDecimal reducedRate = BigDecimal.valueOf(-7);
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            normalPeriods.add(new Period(2, 5));
            reducedPeriods.add(new Period(7, 10));

            new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }
    @Test
    public void RateTestCase7(){
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(2, 5));
        reducedPeriods.add(new Period(7, 10));

        Rate rate1 = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(8, 9);
        BigDecimal charge = rate1.calculate(periodStay);
        assertEquals(new BigDecimal("2"), charge);
    }

    @Test
    public void RateTestCase8() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(3);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(2, 5));
        reducedPeriods.add(new Period(4, 7));

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods));
    }


    //Period
    //Attempt to create a Period with invalid start and end hours
    @Test
    public void PeriodTestCase1(){
        assertThrows(IllegalArgumentException.class, () -> new Period(-1, 5));
    }
    @Test
    public void PeriodTestCase2(){
        assertThrows(IllegalArgumentException.class, () -> new Period(2, 25));
    }
    @Test
    public void PeriodTestCase3(){
        assertThrows(IllegalArgumentException.class, () -> new Period(25, 40));
    }
    @Test
    public void PeriodTestCase4(){
        assertThrows(IllegalArgumentException.class, () -> new Period(5, -2));
    }
    //test Occurrences With IsIn
    @Test
    public void PeriodTestCase5() {
        Period period1 = new Period(2, 5);
        Period period2 = new Period(8, 12);

        List<Period> periodsList = new ArrayList<>();
        periodsList.add(period1);
        periodsList.add(period2);

        assertEquals(3, period1.occurences(periodsList));
        assertEquals(4, period2.occurences(periodsList));
    }

    @Test
    public void PeriodTestCase6(){
        Period period1 = new Period(2, 5);
        Period period3 = new Period(9, 12); // Does not overlap with period1

        assertFalse(period1.overlaps(period3), "Periods should not overlap");
    }

}