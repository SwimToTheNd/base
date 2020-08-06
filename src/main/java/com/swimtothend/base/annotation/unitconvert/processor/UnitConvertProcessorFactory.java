package com.swimtothend.base.annotation.unitconvert.processor;

import com.swimtothend.base.annotation.unitconvert.ConvertAlgorithm;
import com.swimtothend.base.annotation.unitconvert.UnitType;
import com.swimtothend.base.utils.EmptyChecker;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * create by BloodFly at 2019/1/23
 */
public class UnitConvertProcessorFactory {

    private static final String W = "1";  // 元转万元
    private static final String Y = "2";  // 元转亿元
    private static final String S = "3";  // 平方米转平方米
    private static final String A = "4";  // 平方米转亩

    private static Map<String, ConvertAlgorithm> ALGORITHM_MAP;
    private static Map<String, UnitConvertProcessor> PROCESSOR_CACHE;

    static {
        PROCESSOR_CACHE = new HashMap();
        Map<String, ConvertAlgorithm> algorithmMap = new HashMap<>();
        algorithmMap.put(W, new Yuan2WanConvertAlgorithmImpl());
        algorithmMap.put(Y, new Yuan2YiConvertAlgorithmImpl());
        algorithmMap.put(S, new Square2SquareConvertAlgorithmImpl());
        algorithmMap.put(A, new Square2AcreConvertAlgorithmImpl());
        ALGORITHM_MAP = Collections.unmodifiableMap(algorithmMap);
    }

    public static UnitConvertProcessor create(String... unit) {
        if (EmptyChecker.isEmpty(unit)) {
            return null;
        }

        String key = String.join("#", unit);
        UnitConvertProcessor processor = PROCESSOR_CACHE.get(key);
        if (processor != null) {
            return processor;
        }

        LinkedHashMap<UnitType, ConvertAlgorithm> map = new LinkedHashMap<>();
        for (String u : unit) {
            UnitType unitType = getUnitType(u);
            if (EmptyChecker.notEmpty(unitType)) {
                map.put(unitType, ALGORITHM_MAP.get(u));
            }
        }
        processor = new UnitConvertProcessor();
        processor.setTypeAlgorithmMap(map);
        processor.setUnitConvertor(new UnitConvertor());
        PROCESSOR_CACHE.put(key, processor);

        return processor;
    }

    private static UnitType getUnitType(String u) {
        switch (u) {
            case W:
            case Y:
                return UnitType.MONEY;
            case S:
            case A:
                return UnitType.AREA;
            default:
                break;
        }
        return null;
    }
}
