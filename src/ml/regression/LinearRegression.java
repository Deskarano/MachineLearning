package ml.regression;

import ml.matrix.*;
import ml.regression.exceptions.*;

public class LinearRegression
{
    /**
     * The number of training samples
     */
    private int m;

    /**
     * The number of independent variables
     */
    private int n;

    /**
     * Array containing the independent data values
     */
    private double[][] x;

    /**
     * Array containing the dependent data values
     */
    private double[] y;

    /**
     * Vector containing the coefficients of the independent variables.
     */
    private Vector coefficients;

    //the last row in the array is assumed to be the output value
    public LinearRegression(double[][] data)
    {
        m = data[0].length;
        n = data.length - 1;

        x = new double[n][m];
        y = data[n];

        coefficients = new Vector(n + 1);

        System.arraycopy(data, 0, x, 0, n);

        //initialize coefficients
        for(int i = 0; i < n; i++)
        {
            coefficients.set(i, 0);
        }
    }

    /**
     * Performs gradient descent on the data. Each run optimizes the coefficients by a certain amount.
     * The cost function used is the square of the error
     *
     * @param trials The number of times to run gradient descent
     * @param alpha  The learning rate
     */
    public void gradientDescent(int trials, double alpha)
    {
        for(int trial = 0; trial < trials; trial++)
        {
            Vector update = new Vector(x.length + 1);

            //iterate through the different variables
            for(int j = 0; j < coefficients.getDimension(); j++)
            {
                double error = 0;

                for(int i = 0; i < m; i++)
                {
                    double[] dataSet = new double[n];

                    for(int k = 0; k < x.length; k++)
                    {
                        dataSet[k] = x[k][i];
                    }

                    double current = 0;

                    current += getPrediction(dataSet) - y[i];

                    if(j != 0)
                    {
                        current *= dataSet[j - 1];
                    }
                    //else, by definition, the value of dataSet[j - 1] is just 1

                    error += current;
                }

                error = error * alpha / m;
                error = coefficients.get(j) - error;

                update.set(j, error);
            }

            coefficients = update;
        }
    }

    /**
     * Gets the current prediction of the regression using the current coefficients
     *
     * @param args An array containing the arguments, in order, for the independent variables
     * @return The prediction of the model for the supplied arguments
     * @throws RegressionFormatException If the argument array is of the wrong size
     */
    public double getPrediction(double[] args) throws RegressionFormatException
    {
        if(args.length != coefficients.getDimension() - 1)
        {
            throw new RegressionFormatException("arg list is of incorrect size");
        }

        double[] arguments = new double[args.length + 1];
        arguments[0] = 1;

        System.arraycopy(args, 0, arguments, 1, args.length);

        Matrix result = Matrix.multiply(Matrix.transpose(coefficients), new Vector(arguments));
        return result.get(0, 0);
    }

    /**
     * Returns the vector of coefficients
     *
     * @return The vector of coefficients
     */
    public Vector getCoefficients()
    {
        return coefficients;
    }
}
