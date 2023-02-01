package org.anne.common;

import static org.anne.common.Utils.last;

public class ClazzHelper {

    public static int getDayNumberFromClass(Class<?> clazz) {
        return Integer.parseInt(clazz.getSimpleName().substring(3));
    }

    public static int getYearFromClass(Class<?> clazz) {
        return Integer.parseInt(last(clazz.getPackage().toString().split("\\.")).substring(3));
    }
}
