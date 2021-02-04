package util;

import entity.UniVariableRealFunction;

public class Utils {
    public static double[] getArrayOfValuesOfUniVariableRealFunction(
            UniVariableRealFunction function,
            double startOfSegment,
            double endOfSegment,
            int amountOfSegments) {
        double[] x = getArrayOfArguments(startOfSegment, endOfSegment, amountOfSegments - 1);
        return getArrayOfValuesOfUniVariableRealFunction(function, x);
    }

    public static double[] getArrayOfValuesOfUniVariableRealFunction(
            UniVariableRealFunction function,
            double[] x
    ) {
        int amountOfSegments = x.length;
        double[] y = new double[amountOfSegments];
        y[0] = function.value(x[0]);
        for (int i = 0; i < amountOfSegments - 1; i++) {
            y[i + 1] = function.value(x[i + 1]);
        }
        return y;
    }


    public static double[] getArrayOfArguments(double startOfSegment,
                                               double endOfSegment,
                                               int amountOfSegments) {
        double[] x = new double[amountOfSegments];
        double dx = (endOfSegment - startOfSegment) / (amountOfSegments - 1);
        x[0] = startOfSegment;
        for (int i = 1; i < amountOfSegments; i++) {
            x[i] = x[i - 1] + dx;
        }
        return x;
    }

    public static double[] getArrayOfArguments(double startOfSegment,
                                               double endOfSegment,
                                               double step) {
        int amountOfSegments = (int) ((endOfSegment - startOfSegment) / step);
        double[] x = new double[amountOfSegments + 1];
        x[0] = startOfSegment;
        for (int i = 1; i <= amountOfSegments; i++) {
            x[i] = startOfSegment + i * step;
        }
        return x;
    }

}
