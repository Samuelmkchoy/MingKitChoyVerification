package cm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

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

        rate = new Rate(CarParkKind.VISITOR, new BigDecimal("5"), new BigDecimal("2"), normalPeriods, reducedPeriods);
    }

    // Valid Input Test Cases (4)
    @Test
    public void test_001() {
        Period period = new Period(2, 5);
        assertEquals(3, period.duration());
    }

    @Test
    public void test_002() {
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
}

