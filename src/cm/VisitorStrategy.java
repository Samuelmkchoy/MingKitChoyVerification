package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VisitorStrategy implements PayStrategy{
    public BigDecimal applyRateReduction(BigDecimal totalCost) {
        totalCost=BigDecimal.valueOf(20);
        return totalCost;
    }
}
