package ml.regression;

import ml.matrix.*;

public class Hypothesis
{
    private Vector coefficients;

    Hypothesis(int numParameters)
    {
        double[] initial = new double[numParameters];

        for(int i = 0; i < numParameters; i++)
        {
            initial[i] = 0;
        }

        coefficients = new Vector(initial);
    }

    public double prediction(Vector parameters)
    {
        Matrix result = Matrix.multiply(Matrix.transpose(coefficients), parameters);
        return result.get(0, 0);
    }

    public Vector getCoefficients()
    {
        return coefficients;
    }

    public void updateCoefficients(Vector update)
    {
        for(int i = 0; i < update.getDimension(); i++)
        {
            coefficients.set(i, update.get(i));
        }
    }
}
