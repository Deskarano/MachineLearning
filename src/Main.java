import ml.charts.Graph;
import ml.charts.elements.functions.*;

public class Main
{
    public static void main(String[] args)
    {
        Function f1 = TrigFunction.SIN;
        Function f2 = new PolynomialFunction(0, 1);

        Graph g = new Graph(500, 500);
        g.add(Function.add(f1, f2), 0xFFFF0000);
        g.display();
    }
}