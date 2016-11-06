import ml.charts.Graph;
import ml.charts.elements.functions.Function;
import ml.charts.elements.functions.LogFunction;
import ml.charts.elements.functions.PolynomialFunction;
import ml.charts.elements.functions.TrigFunction;

public class Main
{
    public static void main(String[] args)
    {
        PolynomialFunction x = new PolynomialFunction(0, 0, 1);
        Function result = Function.pow(x, x);

        Function der = Function.multiply(result, Function.add(new LogFunction(), 1));

        Graph g = new Graph(500, 500);
        g.add(result, 0xFF0000FF);
        g.add(der, 0xFFFF0000);
        g.display();
    }
}