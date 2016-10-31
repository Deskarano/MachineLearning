import ml.regression.*;

public class Main
{
    public static void main(String[] args)
    {
        double[][] nums =
                {
                        {0, 1, 2, 3, 4},
                        {0, 2, 4, 6, 8}
                };

        double[] arg = {6};


        LinearRegression l = new LinearRegression(nums);

        for(int i = 0; i < 100000; i++)
        {
            l.gradientDescent(.001);
        }

        System.out.println(l.getPrediction(arg));
    }
}
