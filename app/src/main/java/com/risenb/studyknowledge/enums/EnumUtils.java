package com.risenb.studyknowledge.enums;

import java.util.HashMap;
import java.util.Map;

public class EnumUtils {
    private static EnumUtils enumUtils;
    private Map<EnumTAB, Integer> mapEnumTAB = new HashMap<EnumTAB, Integer>();

    public static EnumUtils getEnumUtils() {
        if (enumUtils == null) {
            enumUtils = new EnumUtils();
        }
        return enumUtils;
    }

    public EnumUtils() {
        EnumTAB[] enumArr = EnumTAB.values();
        for (int i = 0; i < enumArr.length; i++) {
            mapEnumTAB.put(enumArr[i], i);
        }
    }

    public Map<EnumTAB, Integer> getMapEnumTAB() {
        return mapEnumTAB;
    }

    public int getIdx(EnumTAB enumTAB) {
        return mapEnumTAB.get(enumTAB);
    }
}
