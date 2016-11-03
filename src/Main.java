import ml.charts.Graph;

import ml.charts.elements.functions.*;


public class Main
{
    public static void main(String[] args)
    {
        TrigFunction cos = new TrigFunction(TrigFunction.COT);

        Graph g = new Graph(500, 500);
        g.add(cos, 0xFFFF0000);

        g.display();
    }

    public static int factorial(int n)
    {
        if(n == 0)
        {
            return 1;
        }

        if(n < 2)
        {
            return n;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }
}

