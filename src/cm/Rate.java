import java.math.BigDecimal;
import java.util.ArrayList;

public class Rate {
    private CarParkKind kind;
    private BigDecimal normalRate;
    private BigDecimal reducedRate;
    private ArrayList<Period> normalPeriods;
    private ArrayList<Period> reducedPeriods;

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods) {
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || reducedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Rates cannot be negative.");
        }
        if (normalRate.compareTo(reducedRate) < 0) {
            throw new IllegalArgumentException("Normal rate should be greater or equal to reduced rate.");
        }
        if (!isValidPeriods(normalPeriods) || !isValidPeriods(reducedPeriods)) {
            throw new IllegalArgumentException("Invalid periods.");
        }

        this.kind = kind;
        this.normalRate = normalRate;
        this.reducedRate = reducedRate;
        this.normalPeriods = normalPeriods;
        this.reducedPeriods = reducedPeriods;
    }

    public BigDecimal calculate(Period periodStay) { //(5, 13)
        int duration = periodStay.duration();
        BigDecimal charge = BigDecimal.ZERO;

        for (Period p : normalPeriods) {
            if (periodStay.overlaps(p)) {
                int overlapDuration = periodStay.calculateOverlap(p);
                charge = charge.add(BigDecimal.valueOf(overlapDuration).multiply(normalRate));
            }
        }

        for (Period p : reducedPeriods) {
            if (periodStay.overlaps(p)) {
                int overlapDuration = periodStay.calculateOverlap(p);
                charge = charge.add(BigDecimal.valueOf(overlapDuration).multiply(reducedRate));
            }
        }

        return charge;
    }

    private boolean isValidPeriods(ArrayList<Period> periods) {
        for (int i = 0; i < periods.size(); i++) {
            Period p1 = periods.get(i);
            for (int j = i + 1; j < periods.size(); j++) {
                Period p2 = periods.get(j);
                if (p1.overlaps(p2)) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Period {
    private int startHour;
    private int endHour;

    public Period(int startHour, int endHour) {
        if (startHour < 0 || startHour >= 24 || endHour < 0 || endHour > 24 || startHour >= endHour) {
            System.out.println("Invalid start or end hour.");
        }
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public boolean overlaps(Period period) {
        return this.startHour < period.endHour && this.endHour > period.startHour;
    }

    public int duration() {
        return endHour - startHour;
    }

    public int calculateOverlap(Period period) {
        int overlapStart = Math.max(this.startHour, period.startHour);
        int overlapEnd = Math.min(this.endHour, period.endHour);
        return overlapEnd - overlapStart;
    }
}

enum CarParkKind {
    SOME_KIND
    // define car park kinds here
}