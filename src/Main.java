import ml.regression.*;

public class Main
{
    public static void main(String[] args)
    {
        double[][] nums =
                {
                        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {0, 2, 4, 6, 8, 10, 12, 14, 16, 18}
                };

        double[] arg = {11};

        LinearRegression l = new LinearRegression(nums);

        for(int i = 0; i < 100000; i++)
        {
            l.gradientDescent(.01);
        }

        System.out.println(l.getPrediction(arg));
    }
}
