package ml.charts.elements.functions;

import ml.matrix.Matrix;
import ml.regression.LinearRegression;

public class PolynomialFunction extends Function
{
    private double[] coefficients;

    public PolynomialFunction(double... coefficients)
    {
        this.coefficients = coefficients;
    }

    public PolynomialFunction(LinearRegression l)
    {
        this(Matrix.transpose(l.getCoefficients()).asArray()[0]);
    }

    public String toString()
    {
        String result = "y = ";

        for(int i = 0; i < coefficients.length; i++)
        {
            if(coefficients[i] != 0)
            {
                if(coefficients[i] != 1)
                {
                    result += coefficients[i];
                }

                result += "x^" + i;

                if(i != coefficients.length - 1)
                {
                    result += " + ";
                }
            }
        }

        return result;
    }

    public double[] getCoefficients()
    {
        return coefficients;
    }

    @Override
    public double getValue(double x)
    {
        double sum = 0;

        for(int i = 0; i < coefficients.length; i++)
        {
            sum += coefficients[i] * Math.pow(x, i);
        }

        return sum;
    }
}
