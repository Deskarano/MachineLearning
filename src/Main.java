
import ml.charts.Graph;
import ml.charts.elements.Scatter;
import ml.charts.elements.functions.PolynomialFunction;
import ml.charts.elements.functions.TrigFunction;
import ml.regression.LinearRegression;

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        double[][] nums = new double[2][100];

        for(int i = 0; i < nums[0].length; i++)
        {
            nums[0][i] = i;
            nums[1][i] = i + (rand.nextBoolean() ? rand.nextInt(5) : -rand.nextInt(5));
        }

        LinearRegression l = new LinearRegression(nums);
        l.normalEquation();

        Graph g = new Graph(500, 500);
        Scatter s = new Scatter(nums);
        PolynomialFunction p = new PolynomialFunction(l);
        TrigFunction t = new TrigFunction(TrigFunction.SIN);

        g.add(s, 0xFFFF0000);
        g.add(p, 0xFF0000FF);
        g.add(t, 0xFF00FF00);

        g.display();
    }
}

