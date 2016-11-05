import ml.charts.Graph;

import ml.charts.elements.Scatter;
import ml.charts.elements.functions.*;
import ml.matrix.Matrix;
import ml.regression.LinearRegression;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        double[][] nums =
                {
                        {3, 2, 6, 3},
                        {7, 34, 74, 6},
                        {83, 2, 4, 56},
                        {7, 2, 3, 4}
                };

        Matrix m = new Matrix(nums);
        Matrix inv = Matrix.inverse(m);

        System.out.println(Matrix.multiply(m, inv));
    }
}

