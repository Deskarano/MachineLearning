
import ml.matrix.Matrix;

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        double[][] nums = new double[5000][5000];

        Random rand = new Random();

        for(int i = 0; i < nums.length; i++)
        {
            for(int j = 0; j < nums[i].length; j++)
            {
                nums[i][j] = rand.nextDouble();
            }
        }

        Matrix m = new Matrix(nums);

        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        Matrix.inverse(m);
        System.out.println(System.currentTimeMillis() - start);
    }
}

