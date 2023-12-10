package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VisitorStrategy implements PayStrategy {
    private static final BigDecimal FREE_THRESHOLD = BigDecimal.valueOf(10);
    private static final BigDecimal REDUCTION_PERCENTAGE = BigDecimal.valueOf(0.5);

    @Override
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        // The first 10.00 is free
        if (totalCost.compareTo(FREE_THRESHOLD) <= 0) {
            return BigDecimal.ZERO;
        }

        totalCost = totalCost.subtract(BigDecimal.TEN);
        totalCost = totalCost.multiply(REDUCTION_PERCENTAGE);
        return totalCost;
    }
}