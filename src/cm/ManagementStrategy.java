package cm;

import java.math.BigDecimal;

public class ManagementStrategy implements PayStrategy {
    private static final BigDecimal MIN_PAYABLE = BigDecimal.valueOf(5);

    @Override
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        return totalCost.min(MIN_PAYABLE);
    }
}
