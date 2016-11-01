import ml.charts.Graph;
import ml.charts.elements.Scatter;

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        double[][] data1 = new double[2][100];
        double[][] data2 = new double[2][100];
        double[][] data3 = new double[2][100];
        
        Random rand = new Random();

        for(int i = 0; i < data1[0].length; i++)
        {
            data1[0][i] = rand.nextDouble() * 10;
            data1[1][i] = rand.nextDouble() * 10;
        }

        for(int i = 0; i < data2[0].length; i++)
        {
            data2[0][i] = rand.nextDouble() * 10;
            data2[1][i] = rand.nextDouble() * 10;
        }

        for(int i = 0; i < data3[0].length; i++)
        {
            data3[0][i] = rand.nextDouble() * 10;
            data3[1][i] = rand.nextDouble() * 10;
        }

        Scatter s1 = new Scatter(data1, 3, 0xFFFF0000, true);
        Scatter s2 = new Scatter(data2, 3, 0xFF0000FF, true);
        Scatter s3 = new Scatter(data3, 3, 0xFF00FF00, true);

        Graph g = new Graph(1000, 500);

        g.add(s1);
        g.add(s2);
        g.add(s3);
        g.display();
    }

    public static void printArray(double[] arr)
    {
        for(double o : arr)
        {
            System.out.print(o + " ");
        }

        System.out.println();
    }
}

