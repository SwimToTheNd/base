package com.swimtothend.base.annotation.unitconvert.processor;

import com.swimtothend.base.annotation.unitconvert.ConvertAlgorithm;

import java.math.BigDecimal;

/**
 * create by BloodFly at 2019/1/23
 */
public class Square2SquareConvertAlgorithmImpl implements ConvertAlgorithm {
    @Override
    public BigDecimal convert(BigDecimal bi) {
        return bi;
    }
}
