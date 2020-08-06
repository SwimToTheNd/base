package com.swimtothend.base.utils;

import java.util.Collection;
import java.util.Map;

/**
 * create by BloodFly at 2019/1/23
 */
public class EmptyChecker {

    private EmptyChecker() {

    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            return ((String) o).trim().length() == 0;
        } else if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        } else if (o instanceof Map) {
            return ((Map) o).isEmpty();
        }
        return false;
    }

    public static boolean notEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 至少有一个为空
     *
     * @param objects
     * @return
     */
    public static boolean isAnyEmpty(Object... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 至少一个不为空
     *
     * @param object
     * @return
     */
    public static boolean isAnyNotEmpty(Object... object) {
        for (Object o : object) {
            if (notEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(Object[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        return false;
    }
}
