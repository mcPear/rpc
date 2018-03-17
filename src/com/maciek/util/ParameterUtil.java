package com.maciek.util;

import java.util.Vector;

public class ParameterUtil {

    public static Object resolveParam(String value, String type) {
        switch (type) {
            case ("Integer"):
                return new Integer(value);
            case ("String"):
                return value;
            case ("Double"):
                return new Double(value);
            case ("Boolean"):
                return new Boolean(value);
            case ("Character"):
                return new Character(value.charAt(0));
            case ("Long"):
                return new Long(value);
            case ("Float"):
                return new Float(value);
            case ("Vector<Double>"):
                return csvToDoubleVector(value);
            default:
                return value;
        }
    }

    private static Vector<Double> csvToDoubleVector(String csv) {
        String[] values = csv.trim().split(",");
        Vector<Double> result = new Vector<>();
        for (int i = 0; i < values.length; i++) {
            result.add(Double.parseDouble(values[i]));
        }
        return result;
    }

}
