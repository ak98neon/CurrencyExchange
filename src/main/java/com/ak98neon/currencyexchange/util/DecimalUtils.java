package com.ak98neon.currencyexchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class DecimalUtils {
    private DecimalUtils() {
        throw new UnsupportedOperationException();
    }

    public static BigDecimal round(BigDecimal value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        return value.setScale(places, RoundingMode.HALF_UP);
    }
}
