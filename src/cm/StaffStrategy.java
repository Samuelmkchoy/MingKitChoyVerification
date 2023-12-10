package cm;

import java.math.BigDecimal;

public class StaffStrategy implements PayStrategy {
    private static final BigDecimal MAX_PAYABLE = BigDecimal.valueOf(10);
    @Override
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        return totalCost.min(MAX_PAYABLE);
    }
}
