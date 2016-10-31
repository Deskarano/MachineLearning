import ml.regression.LinearRegression;

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        double[][] nums = new double[2][100];
        Random rand = new Random();

        for(int i = 0; i < nums[0].length; i++)
        {
            nums[0][i] = i;
        }

        for(int i = 0; i < nums[1].length; i++)
        {
            nums[1][i] = i + rand.nextInt(1);
        }

        LinearRegression l = new LinearRegression(nums);

        l.gradientDescent(100000, .000001);

        double[] arg = {7374};

        System.out.println(l.getPrediction(arg));
    }
}
