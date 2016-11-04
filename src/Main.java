import ml.charts.Graph;

import ml.charts.elements.Scatter;
import ml.charts.elements.functions.*;
import ml.matrix.Matrix;
import ml.regression.LinearRegression;

public class Main
{
    public static void main(String[] args)
    {
        double[][] data =
                {
                        {0, 1, 2, 3, 4, 5},
                        {5, 4, 3, 2, 1, 0}
                };

        LinearRegression l = new LinearRegression(data);
        l.gradientDescent(.0000001, .01, 5);

        Graph g = new Graph(500, 500);
        g.add(new Scatter(data), 0xFF0000FF);
        g.add(new PolynomialFunction(l), 0xFFFF0000);

        g.display();
    }
}

