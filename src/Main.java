import ml.charts.Graph;
import ml.charts.elements.Polynomial;

public class Main
{
    public static void main(String[] args)
    {
        Polynomial p1 = new Polynomial(new double[]{1, 1, 2, 3, 5, 8, 13, 21, 34});
        
        Graph g = new Graph(500, 500);

        g.add(p1, 0xFFFF0000);

        g.display();
    }
}

