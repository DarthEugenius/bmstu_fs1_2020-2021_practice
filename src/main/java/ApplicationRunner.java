import org.apache.commons.math3.util.FastMath;
import running.BoundaryValueProblemSolveApplication;

public class ApplicationRunner {
    /**
     * epsilon * y''(x) + p(x) * y'(x) + q(x) * y(x) + f(x) = 0
     * x in [a; b]
     * -alpha_1 * y'(a) + alpha_2 * y(a) = gamma_1
     * beta_1 * y'(b) + beta_2 * y(b) = gamma_2
     */
    public static void main(String[] args) {

        BoundaryValueProblemSolveApplication.run(
                // p(x) =
                x -> x * FastMath.sin(x) + FastMath.cos(x),
                // q(x) =
                x -> FastMath.pow(x, 2) * FastMath.sin(x) + FastMath.cos(x),
                // f(x) =
                x -> FastMath.pow(x, 3) - FastMath.pow(x, 2) + FastMath.sqrt(x),
                // a =
                0,
                // b =
                1,
                // alpha_1 =
                1,
                // alpha_2 =
                0,
                // beta_1 =
                1,
                // beta_2 =
                0,
                // gamma_1 =
                2,
                // gamma_2 =
                5,
                // h =
                0.001
        );
    }
}




