
import ml.charts.Graph;
import ml.charts.elements.Polynomial;
import ml.charts.elements.Scatter;
import ml.regression.LinearRegression;

public class Main
{
    public static void main(String[] args)
    {
        Polynomial p1 = new Polynomial(new double[]{0, 0, 0, 1});
        Polynomial p2 = Polynomial.derivate(p1);
        Polynomial p3 = Polynomial.derivate(p2);

        Graph g = new Graph(500, 500);

        g.add(p1, 0xFFFF0000);
        g.add(p2, 0xFF0000FF);
        g.add(p3, 0xFF00FF00);

        g.display();
    }
}

