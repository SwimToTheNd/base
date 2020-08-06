package com.swimtothend.base.annotation.unitconvert.processor;

import com.swimtothend.base.annotation.unitconvert.ConvertAlgorithm;
import com.swimtothend.base.annotation.unitconvert.UnitConvert;
import com.swimtothend.base.annotation.unitconvert.UnitType;
import com.swimtothend.base.utils.EmptyChecker;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 注解UnitConvert处理器
 * create by BloodFly at 2019/1/23
 */
public class UnitConvertProcessor {
    private static final Map<Class<?>, List<UnitTypeField>> UNIT_FIELD_CACHE = new HashMap<>();
    private UnitConvertor unitConvertor;
    private LinkedHashMap<UnitType, ConvertAlgorithm> typeAlgorithmMap;

    public Object convertObject(Object obj) {
        ConvertAlgorithm ca = getConvertAlgorithm();
        if (EmptyChecker.isEmpty(obj) || ca == null) {
            return obj;
        }

        if (isNormalClass(obj)) {
            return unitConvertor.convert(obj, ca);
        }

        if (obj instanceof List) {
            convertObject(obj, ca);
            return obj;
        }

        // 自定义类
        if (isCustomType(obj.getClass().getName())) {
            convertCustomType(obj);
        }

        return obj;
    }

    public void convertCustomType(Object obj) {
        if (obj == null) {
            return;
        }

        Class<?> clazz = obj.getClass();
        List<UnitTypeField> unitTypeFields = UNIT_FIELD_CACHE.get(clazz);
        if (EmptyChecker.isEmpty(unitTypeFields)) {
            unitTypeFields = new ArrayList<>();
            Class<?> tempClazz = clazz;
            do {
                unitTypeFields.addAll(getDeclaredField(tempClazz));
            } while ((tempClazz = tempClazz.getSuperclass()) != null);

            UNIT_FIELD_CACHE.put(clazz, unitTypeFields);
        }

        try {
            for (UnitTypeField field : unitTypeFields) {
                convertObjectField(obj, field);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void convertObjectField(Object obj, UnitTypeField unitTypeField) throws IllegalAccessException {
        UnitType unitType = unitTypeField.getUnitType();
        ConvertAlgorithm ca = getConvertAlgorithm(unitType);
        if (ca == null) {
            return;
        }

        Field field = unitTypeField.getField();
        Type type = field.getGenericType();

        // 类
        if (type instanceof Class) {
            if (isNormalClass(type)) {
                field.setAccessible(true);
                field.set(obj, unitConvertor.convert(field.get(obj), ca));
            }
            // 自定义类对象
            else {
                field.setAccessible(true);
                field.set(obj, convertObject(field.get(obj), ca));
            }
        }

        // 泛型类型变量
        if (type instanceof TypeVariable) {
            field.setAccessible(true);
            field.set(obj, convertObject(field.get(obj), ca));
        }

        // 参数化类型 Collection<String>
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (List.class.equals(rawType)) {
                convertList(obj, field, ca);
            }

            if (isCustomType(rawType.getTypeName())) {
                field.setAccessible(true);
                field.set(obj, convertObject(field.get(obj), ca));
            }
        }

        // 泛型数组类型  T[]
        if (type instanceof GenericArrayType) {

        }

        // 通配符类型  ?, ? extends Number, ? super Integer
        if (type instanceof WildcardType) {

        }
    }

    private void convertList(Object obj, Field field, ConvertAlgorithm ca) throws IllegalAccessException {
        field.setAccessible(true);
        List<?> list = (List<?>) field.get(obj);
        if (EmptyChecker.isEmpty(list)) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            field.set(obj, convertObject(list.get(i), ca));
        }
    }

    /**
     * 指定转换算法转换对象
     *
     * @param o
     * @param ca
     * @return
     */
    private Object convertObject(Object o, ConvertAlgorithm ca) {
        if (o == null || ca == null) {
            return o;
        }
        // 一般类
        if (isNormalClass(o)) {
            return unitConvertor.convert(o, ca);
        }

        // List集合
        if (o instanceof List) {
            convertList((List<?>) o, ca);
            return o;
        }

        // 自定义类型
        if (isCustomType(o.getClass().getName())) {
            convertCustomType(o);
            return o;
        }

        return o;
    }

    private List<UnitTypeField> getDeclaredField(Class<?> tempClazz) {
        List<UnitTypeField> typeFields = new ArrayList<>();
        Field[] declaredFields = tempClazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            UnitConvert annotation = declaredField.getDeclaredAnnotation(UnitConvert.class);
            if (annotation != null) {
                typeFields.add(new UnitTypeField(annotation.value(), declaredField));
            }
        }
        return typeFields;
    }

    private <T> void convertList(List<T> list, ConvertAlgorithm ca) {
        if (EmptyChecker.isEmpty(list) || ca == null) {
            return;
        }

        T t = list.get(0);
        // 一般类
        if (isNormalClass(t)) {
            convertNormalList(list, ca);
        }
        // 其他
        else {
            convertCompound(list, ca);
        }

    }

    private <T> void convertCompound(List<T> list, ConvertAlgorithm ca) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, (T) convertObject(list.get(i), ca));
        }
    }

    private <T> void convertNormalList(List<T> list, ConvertAlgorithm ca) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, unitConvertor.convert(list.get(i), ca));
        }
    }

    private ConvertAlgorithm getConvertAlgorithm(UnitType unitType) {
        return typeAlgorithmMap.get(unitType);
    }

    private ConvertAlgorithm getConvertAlgorithm() {
        if (!typeAlgorithmMap.isEmpty()) {
            return typeAlgorithmMap.entrySet().iterator().next().getValue();
        }
        return null;
    }


    private boolean isNormalClass(Type type) {
        if (String.class.equals(type)) {
            return true;
        }

        if (Double.class.equals(type)) {
            return true;
        }

        if (Float.class.equals(type)) {
            return true;
        }

        if (BigDecimal.class.equals(type)) {
            return true;
        }

        return false;
    }

    private boolean isNormalClass(Object o) {
        if (o instanceof String) {
            return true;
        }
        if (o instanceof Double) {
            return true;
        }
        if (o instanceof Float) {
            return true;
        }
        if (o instanceof BigDecimal) {
            return true;
        }
        return false;
    }

    private boolean isCustomType(String className) {
        if (!className.startsWith("java.lang") && !className.startsWith("java.util")) {
            return true;
        }
        return false;
    }

    public UnitConvertor getUnitConvertor() {
        return unitConvertor;
    }

    public void setUnitConvertor(UnitConvertor unitConvertor) {
        this.unitConvertor = unitConvertor;
    }

    public LinkedHashMap<UnitType, ConvertAlgorithm> getTypeAlgorithmMap() {
        return typeAlgorithmMap;
    }

    public void setTypeAlgorithmMap(LinkedHashMap<UnitType, ConvertAlgorithm> typeAlgorithmMap) {
        this.typeAlgorithmMap = typeAlgorithmMap;
    }
}

class UnitTypeField {
    private UnitType unitType;
    private Field field;

    public UnitTypeField() {
    }

    public UnitTypeField(UnitType unitType, Field field) {
        this.unitType = unitType;
        this.field = field;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
