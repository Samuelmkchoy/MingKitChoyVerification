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


public class MingKitChoyTestTask3 {

        // Valid Input Test Cases (4)
        @Test
        public void test_001() {
            Period period = new Period(2, 5);
            assertEquals(3, period.duration());
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


        //Task 2
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
        public void PeriodTestCase6() {
            Period period1 = new Period(2, 5);
            Period period3 = new Period(9, 12); // Does not overlap with period1

            assertFalse(period1.overlaps(period3), "Periods should not overlap");
        }

    //Task 3
    @Test
    public void StaffCharge(){
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(7, 24));
        reducedPeriods.add(new Period(0, 7));

        Rate rate1 = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(0, 24);
        BigDecimal charge = rate1.calculate(periodStay);
        //STAFF: The maximum payable is 10.00 per day.
        assertEquals(new BigDecimal("10"), charge);
    }
    @Test
    public void ManagementCharge(){
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(7, 24));
        reducedPeriods.add(new Period(0, 7));

        Rate rate1 = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(0, 24);
        BigDecimal charge = rate1.calculate(periodStay);
        //MANAGEMENT: minimum payable is 5.00
        assertEquals(new BigDecimal("5"), charge);
    }
    @Test
    public void StudentCharge(){
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(7, 24));
        reducedPeriods.add(new Period(0, 7));

        Rate rate1 = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(0, 5);
        BigDecimal charge = rate1.calculate(periodStay);
        //STUDENT: 33% reduction on any amount above 5.50
        //This should be 10 - 5.5 =4.5
        // 4.5 * 0.33 = 1.485
        // 5.5 + 1.485 = 6.985
        assertEquals(new BigDecimal("6.985"), charge);
    }
    @Test
    public void StudentCharge2(){
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(7, 24));
        reducedPeriods.add(new Period(0, 7));

        Rate rate1 = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(0, 1);
        BigDecimal charge = rate1.calculate(periodStay);
        //STUDENT: 33% reduction on any amount above 5.50
        // less than 5. it won't change.
        assertEquals(new BigDecimal("2"), charge);
    }
    @Test
    public void VisitorCharge(){
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(7, 24));
        reducedPeriods.add(new Period(0, 7));

        Rate rate1 = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(14, 24);
        BigDecimal charge = rate1.calculate(periodStay);
        // According to the specified logic, the first 10.00 is free,
        // and there is a 50% reduction on any amount above that.
        // (50 - 10) * 0.5 = 20
        assertEquals(new BigDecimal("20.0"), charge);
    }
    @Test
    public void VisitorNoCharge(){
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(7, 24));
        reducedPeriods.add(new Period(0, 7));

        Rate rate1 = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(14, 15);
        BigDecimal charge = rate1.calculate(periodStay);
        // According to the specified logic, the first 10.00 is free,
        // and there is a 50% reduction on any amount above that.
        // (50 - 10) * 0.5 = 20
        assertEquals(new BigDecimal("0"), charge);
    }
    @Test
    public void KindIsNull(){
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        normalPeriods.add(new Period(7, 24));
        reducedPeriods.add(new Period(0, 7));

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(null, normalRate, reducedRate, normalPeriods, reducedPeriods)
        );
    }
    @Test
    public void invalidPeriodsTest() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.valueOf(5);
        BigDecimal reducedRate = BigDecimal.valueOf(2);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7, 12));
        normalPeriods.add(new Period(10, 15));  // Overlaps with the previous period

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(0, 7));

        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }
}