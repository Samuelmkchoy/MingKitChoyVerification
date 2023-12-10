package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface PayStrategy {
        BigDecimal applyRateReduction(BigDecimal totalCost);
}
