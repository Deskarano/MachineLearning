import ml.charts.Graph;
import ml.charts.elements.Polynomial;
import ml.charts.elements.Scatter;
import ml.regression.LinearRegression;

public class Main
{
    public static void main(String[] args)
    {
        Polynomial p1 = new Polynomial(new double[]{1, 0, 1});
        Polynomial p2 = new Polynomial((new double[] {0, 1}));

        Polynomial result = Polynomial.multiply(p1, p2);

        Graph g = new Graph(500, 500);

        g.add(result, 0xFF0000FF);

        g.display();
    }
}

