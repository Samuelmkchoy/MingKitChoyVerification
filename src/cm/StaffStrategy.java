package cm;

import java.math.BigDecimal;

public class StaffStrategy  implements PayStrategy{
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        return totalCost;
    }
}
