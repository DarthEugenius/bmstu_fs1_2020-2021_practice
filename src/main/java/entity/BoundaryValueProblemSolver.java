package entity;

    /**
     * epsilon * y''(x) + p(x) * y'(x) + q(x) * y(x) + f(x) = 0
     * x in [startOfSegment; endOfSegment]
     * -alpha_1 * y'(startOfSegment) + alpha_2 * y(startOfSegment) = gamma_1
     * beta_1 * y'(endOfSegment) + beta_2 * y(endOfSegment) = gamma_2
     */
public interface BoundaryValueProblemSolver {

    double[] solve(
            double epsilon,
            UniVariableRealFunction p,
            UniVariableRealFunction q,
            UniVariableRealFunction f,
            double startOfSegment,
            double endOfSegment,
            double alphaFirst,
            double alphaSecond,
            double betaFirst,
            double betaSecond,
            double gammaFirst,
            double gammaSecond,
            double step
    );
}
