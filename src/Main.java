import ml.charts.Graph;

import ml.charts.elements.functions.*;

public class Main
{
    public static void main(String[] args)
    {
        PolynomialFunction e = new PolynomialFunction(Math.E);
        PolynomialFunction x = new PolynomialFunction(0, 1);

        Function result = Function.pow(e, x);

        Graph g = new Graph(500, 500);
        g.add(result, 0xFF0000FF);

        g.display();
    }
}

