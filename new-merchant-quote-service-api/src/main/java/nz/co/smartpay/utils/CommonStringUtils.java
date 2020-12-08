package nz.co.smartpay.utils;

import java.util.Arrays;

public class CommonStringUtils {

    public static String joinWithSpace(String... lines) {
        return String.join(",", Arrays.asList(lines));
    }

}
