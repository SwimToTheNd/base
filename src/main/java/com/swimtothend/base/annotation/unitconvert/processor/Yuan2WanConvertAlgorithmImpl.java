package com.swimtothend.base.annotation.unitconvert.processor;

import com.swimtothend.base.annotation.unitconvert.ConvertAlgorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 元转万元算法
 * create by BloodFly at 2019/1/23
 */
public class Yuan2WanConvertAlgorithmImpl implements ConvertAlgorithm {
    private static final BigDecimal PER = new BigDecimal("10000");

    @Override
    public BigDecimal convert(BigDecimal bi) {
        return bi.divide(PER, 2, RoundingMode.HALF_UP);
    }
}
