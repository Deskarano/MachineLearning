
import ml.charts.Graph;
import ml.charts.elements.Polynomial;
import ml.charts.elements.Scatter;
import ml.regression.LinearRegression;

public class Main
{
    public static void main(String[] args)
    {
        double data[][] =
                {
                        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        {0, 2, 2, 3, 4, 3, 6, 7, 11, 9, 10}
                };

        double[] arg = {5};

        LinearRegression l = new LinearRegression(data);
        l.gradientDescent(arg, .000001, .001);

        Scatter plot = new Scatter(data, 4, 0xFFFF0000, true);
        Polynomial reg = new Polynomial(l, 0xFF0000FF);

        Graph g = new Graph(500, 500);

        g.add(plot);
        g.add(reg);

        g.display();
    }
}

