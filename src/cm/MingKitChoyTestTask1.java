package cm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateTest {
    private Rate rate;

    @BeforeEach
    public void setUp() {
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2, 5));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 10));

        rate = new Rate(CarParkKind.SOME_KIND, new BigDecimal("5"), new BigDecimal("2"), normalPeriods, reducedPeriods);
    }

    @Test
    public void testCalculateWithNoOverlap() {
        Period periodStay = new Period(12, 1);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(BigDecimal.ZERO, charge);
    }

    @Test
    public void testCalculateWithNormalRateOnly() {
        Period periodStay = new Period(3, 4);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("5"), charge);
    }

    @Test
    public void testCalculateWithReducedRateOnly() {
        Period periodStay = new Period(8, 9);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("2"), charge);
    }

    @Test
    public void testCalculateWithOverlap() {
        Period periodStay = new Period(4, 8);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(new BigDecimal("7"), charge);
    }

    @Test
    public void test02() {
        Period periodStay = new Period(99, 1);
        BigDecimal charge = rate.calculate(periodStay);
        assertEquals(BigDecimal.ZERO, charge);
    }

}
