package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StudentStrategy implements PayStrategy{
    private static final BigDecimal REDUCTION_THRESHOLD = BigDecimal.valueOf(5.50);
    private static final BigDecimal REDUCTION_PERCENTAGE = BigDecimal.valueOf(0.33);

    @Override
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        // Apply the Student reduction: 33% reduction on any amount above 5.50
        if (totalCost.compareTo(REDUCTION_THRESHOLD) > 0) {
            BigDecimal reductionAmount = totalCost.subtract(REDUCTION_THRESHOLD)
                    .multiply(REDUCTION_PERCENTAGE);
            return REDUCTION_THRESHOLD.add(reductionAmount);
        }
        return totalCost;
    }
}
