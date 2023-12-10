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
}