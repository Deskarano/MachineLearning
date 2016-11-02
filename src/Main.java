import ml.charts.Graph;
import ml.charts.elements.Polynomial;
import ml.charts.elements.Scatter;
import ml.regression.LinearRegression;

public class Main
{
    public static void main(String[] args)
    {
        double[][] data =
                {
                        {0, 1, 2, 3, 2.6},
                        {0, 2, 1, 3, 5}
                };

        Scatter s = new Scatter(data);
        Graph g = new Graph(500, 500);

        LinearRegression l = new LinearRegression(data);
        l.gradientDescent(10000, .001);

        g.add(s, 0xFFFF0000);
        g.add(new Polynomial(l), 0xFF0000FF);
        g.display();
    }
}

