import ml.charts.Graph;

import ml.charts.elements.functions.*;
import ml.matrix.Matrix;

public class Main
{
    public static void main(String[] args)
    {
        double[][] nums1 =
                {
                        {5, 2, 3, 4},
                        {4, 5, 6, 2},
                        {7, 8, 9, 1},
                        {9, 8, 5, 4}
                };

        Matrix m1 = new Matrix(nums1);

        System.out.println(m1.determinant());

    }
}

