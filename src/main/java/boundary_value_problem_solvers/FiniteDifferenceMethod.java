package boundary_value_problem_solvers;

import entity.BoundaryValueProblemSolver;
import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;
import util.Utils;

public class FiniteDifferenceMethod implements BoundaryValueProblemSolver {

    @Override
    public double[] solve(
            double epsilon,
            UniVariableRealFunction initialP,
            UniVariableRealFunction initialQ,
            UniVariableRealFunction initialF,
            double a,
            double b,
            double alpha1,
            double alpha2,
            double beta1,
            double beta2,
            double gamma1,
            double gamma2,
            double h) {
        UniVariableRealFunction p = x -> initialP.value(x) / epsilon;
        UniVariableRealFunction q = x -> initialQ.value(x) / epsilon;
        UniVariableRealFunction f = x -> initialF.value(x) / epsilon;
        double[] arrayOfArguments = Utils.getArrayOfArguments(
                a,
                b,
                h);
        double[] aArray = new double[arrayOfArguments.length];
        double[] bArray = new double[arrayOfArguments.length];
        double[] cArray = new double[arrayOfArguments.length];
        double[] bVector = new double[arrayOfArguments.length];
        aArray[0] = h * alpha2 + alpha1;
        bArray[0] = -alpha1;
        cArray[0] = 0;
        bVector[0] = h * gamma1;
        for (int i = 1; i < arrayOfArguments.length - 1; i++) {
            double x = arrayOfArguments[i];
            cArray[i] = 1 - h / 2 * p.value(x);
            aArray[i] = FastMath.pow(h, 2) * q.value(x) - 2;
            bArray[i] = 1 + h / 2 * p.value(x);
            bVector[i] = -FastMath.pow(h, 2) * f.value(x);
        }
        bArray[arrayOfArguments.length - 1] = 0;
        cArray[arrayOfArguments.length - 1] = -beta1;
        aArray[arrayOfArguments.length - 1] = h * beta2 + beta1;
        bVector[arrayOfArguments.length - 1] = h * gamma2;
        return ThomasMethod.solve(
                aArray,
                bArray,
                cArray,
                bVector);
    }

    static class ThomasMethod {
        static double[] solve(double[] aArray,
                              double[] bArray,
                              double[] cArray,
                              double[] bVector) {
            double alpha;
            double[] xVector = new double[bVector.length];
            for (int i = 1; i < bVector.length; i++) {
                alpha = cArray[i - 1] / (-aArray[i - 1]);
                aArray[i] += alpha * bArray[i - 1]; // -a[i]-c[i]*P[i] <- в обозначениях Коновалова
                bVector[i] += alpha * bVector[i - 1]; // c[i]*Q[i]-d[i]
            }
            xVector[bVector.length - 1] = bVector[bVector.length - 1] / aArray[bVector.length - 1];
            for (int i = bVector.length - 2; i >= 0; i--) {
                xVector[i] = (bVector[i] - bArray[i] * xVector[i + 1]) / aArray[i];
            }
            return xVector;
        }
    }
}
