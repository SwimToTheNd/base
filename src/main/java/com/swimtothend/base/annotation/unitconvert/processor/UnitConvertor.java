package com.swimtothend.base.annotation.unitconvert.processor;

import com.swimtothend.base.annotation.unitconvert.ConvertAlgorithm;
import com.swimtothend.base.annotation.unitconvert.TypeConvert;
import com.swimtothend.base.utils.EmptyChecker;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * create by BloodFly at 2019/1/23
 */
public class UnitConvertor {

    private static Map<Class, TypeConvert> TYPE_CONVERT_MAP;

    static {
        Map<Class, TypeConvert> map = new HashMap<>();
        map.put(Double.class, new TypeConvert.DoubleTypeConvertImpl());
        map.put(Float.class, new TypeConvert.FloatTypeConvertImpl());
        map.put(BigDecimal.class, new TypeConvert.BigDecimalTypeConvertImpl());
        map.put(String.class, new TypeConvert.StringTypeConvertImpl());
        TYPE_CONVERT_MAP = Collections.unmodifiableMap(map);
    }

    /**
     * 单位转换
     *
     * @param i   值
     * @param ca  转换算法
     * @param <T> 值类型
     * @return 转换后的值
     */
    public <T> T convert(T i, ConvertAlgorithm ca) {
        if (EmptyChecker.isEmpty(i)) {
            return i;
        }
        TypeConvert<T> typeConvert = TYPE_CONVERT_MAP.get(i.getClass());
        if (typeConvert == null) {
            return i;
        }
        BigDecimal result = ca.convert(typeConvert.convert2BigDecimal(i));
        return typeConvert.convertBack2Type(result);
    }

    public static void main(String[] args) {
        Double d = 1234552.2d;
        System.out.println(Double.class == d.getClass());

        UnitConvertor unitConvert = new UnitConvertor();
        String convert = unitConvert.convert("", new Yuan2WanConvertAlgorithmImpl());
        System.out.println(convert);

    }
}
