package entity;

/**
 * A {@code @FunctionalInterface} to represent function as a lambda expression. For example: <em>f(x) = log(x)</em>
 * can be represented as {@code x -> FastMath.log(x)}, or {@code FastMath::log), as a method reference.
 *
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 *
 * @version 1.0
 */
@FunctionalInterface
public interface UniVariableRealFunction {

    double value(double x);
}