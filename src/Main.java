import ml.charts.Graph;
import ml.charts.elements.functions.TrigFunction;

public class Main
{
    public static void main(String[] args)
    {
        TrigFunction t = new TrigFunction(TrigFunction.SIN);

        Graph g = new Graph(500, 500);
        g.add(t, 0xFFFF0000);

        g.display();
    }
}