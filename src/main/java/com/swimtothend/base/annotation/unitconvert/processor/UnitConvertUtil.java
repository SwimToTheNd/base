package com.swimtothend.base.annotation.unitconvert.processor;

import java.math.BigDecimal;
import java.util.List;

/**
 * create by BloodFly at 2019/1/23
 */
public class UnitConvertUtil {

    public static String convert(String i, String unit) {
        UnitConvertProcessor processor = UnitConvertProcessorFactory.create(unit);
        if (processor == null) {
            return i;
        }
        return (String) processor.convertObject(i);
    }

    public static Double convert(Double i, String unit) {
        UnitConvertProcessor processor = UnitConvertProcessorFactory.create(unit);
        if (processor == null) {
            return i;
        }
        return (Double) processor.convertObject(i);
    }

    public static Float convert(Float i, String unit) {
        UnitConvertProcessor processor = UnitConvertProcessorFactory.create(unit);
        if (processor == null) {
            return i;
        }
        return (Float) processor.convertObject(i);
    }

    public static BigDecimal convert(BigDecimal i, String unit) {
        UnitConvertProcessor processor = UnitConvertProcessorFactory.create(unit);
        if (processor == null) {
            return i;
        }
        return (BigDecimal) processor.convertObject(i);
    }

    public static Object convert(Object i, String... unit) {
        UnitConvertProcessor processor = UnitConvertProcessorFactory.create(unit);
        if (processor == null) {
            return i;
        }
        return processor.convertObject(i);
    }

    public static Object convert(List<?> i, String... unit) {
        UnitConvertProcessor processor = UnitConvertProcessorFactory.create(unit);
        if (processor == null) {
            return i;
        }
        return processor.convertObject(i);
    }


    public static void main(String[] args) {
        System.out.println(convert(1234656789.99, "2"));
    }
}
