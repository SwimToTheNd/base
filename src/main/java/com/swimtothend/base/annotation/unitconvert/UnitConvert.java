package com.swimtothend.base.annotation.unitconvert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by BloodFly at 2019/1/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UnitConvert {

    UnitType value() default UnitType.MONEY;
}
