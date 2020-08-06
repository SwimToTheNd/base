package com.swimtothend.base.annotation.unitconvert;

import java.math.BigDecimal;

public interface TypeConvert<T> {
    BigDecimal convert2BigDecimal(T t);

    T convertBack2Type(BigDecimal b);

    class DoubleTypeConvertImpl implements TypeConvert<Double> {

        @Override
        public BigDecimal convert2BigDecimal(Double o) {
            return new BigDecimal(o);
        }

        @Override
        public Double convertBack2Type(BigDecimal b) {
            return b.doubleValue();
        }
    }

    class FloatTypeConvertImpl implements TypeConvert<Float> {

        @Override
        public BigDecimal convert2BigDecimal(Float o) {
            return new BigDecimal(o);
        }

        @Override
        public Float convertBack2Type(BigDecimal b) {
            return b.floatValue();
        }
    }

    class BigDecimalTypeConvertImpl implements TypeConvert<BigDecimal> {

        @Override
        public BigDecimal convert2BigDecimal(BigDecimal o) {
            return o;
        }

        @Override
        public BigDecimal convertBack2Type(BigDecimal b) {
            return b;
        }
    }

    class StringTypeConvertImpl implements TypeConvert<String> {

        @Override
        public BigDecimal convert2BigDecimal(String o) {
            return new BigDecimal(o);
        }

        @Override
        public String convertBack2Type(BigDecimal b) {
            return b.toString();
        }
    }
}
