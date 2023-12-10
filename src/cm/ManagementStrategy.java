package cm;

import java.math.BigDecimal;

public class ManagementStrategy implements PayStrategy{
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        totalCost = BigDecimal.valueOf(5);
        return totalCost;
    }
}
