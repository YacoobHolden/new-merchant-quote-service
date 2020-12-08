package nz.co.smartpay.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    public static BigDecimal linearInterpolate(BigDecimal x, BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2) {
        // Formula is y = y1 + (x - x1) * (y1 - y2)/(x2-x1)
        return y1.add((x.subtract(x1)).multiply((y2.subtract(y1)).divide(x2.subtract(x1), 32, RoundingMode.FLOOR)));
    }
}
