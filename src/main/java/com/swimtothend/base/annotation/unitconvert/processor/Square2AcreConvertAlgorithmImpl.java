package com.swimtothend.base.annotation.unitconvert.processor;

import com.swimtothend.base.annotation.unitconvert.ConvertAlgorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 平方米转亩算法
 * create by BloodFly at 2019/1/23
 */
public class Square2AcreConvertAlgorithmImpl implements ConvertAlgorithm {
    private static final BigDecimal PER = new BigDecimal("0.0015");

    @Override
    public BigDecimal convert(BigDecimal bi) {
        return bi.multiply(PER).setScale(2, RoundingMode.HALF_UP);
    }
}
