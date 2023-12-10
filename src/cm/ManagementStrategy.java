package cm;

import java.math.BigDecimal;

public class ManagementStrategy implements PayStrategy{
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        return totalCost;
    }
}
