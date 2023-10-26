package cm;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Creating sample periods
        Period period1 = new Period(2, 5);
        Period period2 = new Period(8, 2);
        Period period3 = new Period(1, 6);

        // Creating sample rate
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(period1);
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(period2);

        Rate rate = new Rate(CarParkKind.SOME_KIND, BigDecimal.valueOf(5), BigDecimal.valueOf(2), normalPeriods, reducedPeriods);

        System.out.println("Duration of period 1: " + period1.duration());
        System.out.println("cm.Period 1 overlaps period 2: " + period1.overlaps(period2));
        System.out.println("Charge for period 2: " + rate.calculate(period2));
        System.out.println("Charge for period 3: " + rate.calculate(period3));
    }
}
