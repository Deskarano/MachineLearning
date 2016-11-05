import ml.charts.Graph;
import ml.charts.elements.functions.Function;
import ml.charts.elements.functions.PolynomialFunction;
import ml.charts.elements.functions.TrigFunction;

public class Main
{
    public static void main(String[] args)
    {
        Function f1= new PolynomialFunction(0, 0, 1);
        Function f2 = new PolynomialFunction(0, 2);

        Function result = Function.concatenate(f1, f2);
        result = Function.multiply(f1, result);

        Graph g = new Graph(500, 500);

        g.add(result, 0xFF00FF00);

        g.display();
    }
}

