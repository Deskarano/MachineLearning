package ml.regression;

import ml.matrix.*;

public class LinearRegression
{
    private double[][] x;
    private double[] y;
    private Hypothesis h;

    //the last row in the array is assumed to be the output value
    public LinearRegression(double[][] data)
    {
        x = new double[data.length][data[0].length];
        y = data[data.length - 1];

        for(int i = 0; i < x[0].length; i++)
        {
            x[0][i] = 1;
        }

        for(int i = 0; i < data.length - 1; i++)
        {
            x[i + 1] = data[i];
        }

        h = new Hypothesis(data.length);
    }


    public void gradientDescent(double alpha)
    {
        Vector update = new Vector(x.length);

        //iterate through the different variables
        for(int j = 0; j < x.length; j++)
        {
            double sum = 0;

            //iterate through every dataset
            for(int i = 0; i < x[j].length; i++)
            {
                double[] dataSet = new double[x.length];

                for(int k = 0; k < x.length; k++)
                {
                    dataSet[k] = x[k][i];
                }

                double current = 0;

                current += h.prediction(new Vector(dataSet)) - y[i];
                current *= x[j][i];

                sum += current;
            }

            sum = sum * alpha / (x[j].length + 1);
            sum = h.getCoefficients().get(j) - sum;

            update.set(j, sum);
        }

        h.updateCoefficients(update);
    }

    //TODO: simplify code to use this method more
    public double getPrediction(double[] args)
    {
        double[] arguments = new double[args.length + 1];
        arguments[0] = 1;

        for(int i = 0; i < args.length; i++)
        {
            arguments[i + 1] = args[i];
        }

        return h.prediction(new Vector(arguments));
    }

    private void printArray(double[] arr)
    {
        for( double d : arr)
        {
            System.out.print(d + " ");
        }

        System.out.println();
    }
}
