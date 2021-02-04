package running;

import boundary_value_problem_solvers.FiniteDifferenceMethod;
import entity.UniVariableRealFunction;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import util.Utils;

public class BoundaryValueProblemSolveApplication {
    public static final double[] EPSILONS = {
            1,
            0.1,
            0.01,
            0.001
    };

    public static void run(
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
            double h
    ) {
        final FiniteDifferenceMethod finiteDifferenceMethod = new FiniteDifferenceMethod();
        XYChart chart = new XYChartBuilder()
                .width(900)
                .height(600)
                .title("РЕШЕНИЕ")
                .theme(Styler.ChartTheme.Matlab)
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();
        double[] arrayOfArguments = Utils.getArrayOfArguments(0, 1, h);
        for (double epsilon : EPSILONS) {
            double[] solution = finiteDifferenceMethod.solve(
                    epsilon,
                    initialP,
                    initialQ,
                    initialF,
                    a,
                    b,
                    alpha1,
                    alpha2,
                    beta1,
                    beta2,
                    gamma1,
                    gamma2,
                    h
            );
            XYSeries currentSeries = chart.addSeries(
                    "epsilon = " + epsilon,
                    arrayOfArguments,
                    solution
            );
            currentSeries.setMarker(SeriesMarkers.NONE);
            currentSeries.setSmooth(false);
        }
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideS);
        chart.getStyler().setZoomEnabled(true);
        new SwingWrapper<Chart<XYStyler, XYSeries>>(chart).displayChart();
    }
}
