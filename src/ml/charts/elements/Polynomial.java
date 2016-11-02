package ml.charts.elements;

import ml.matrix.Matrix;
import ml.regression.LinearRegression;

public class Polynomial extends Function
{
    private double[] coefficients;

    public Polynomial(double[] coefficients)
    {
        this.coefficients = coefficients;
    }

    public Polynomial(LinearRegression l)
    {
        this(Matrix.transpose(l.getCoefficients()).asArray()[0]);
    }


    public String toString()
    {
        String result = "y = " + coefficients[0] + " + ";

        for(int i = 1; i < coefficients.length; i++)
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

    @Override
    public double[] getPreferredDomain()
    {
        return null;
    }

    @Override
    public double[] getPreferredRange()
    {
        return null;
    }

    public static Polynomial add(Polynomial p1, Polynomial p2)
    {
        double[] p1Coefficients = p1.getCoefficients();
        double[] p2Coefficients = p2.getCoefficients();

        double[] newCoefficients = new double[Math.max(p1Coefficients.length, p2Coefficients.length)];

        for(int i = 0; i < newCoefficients.length; i++)
        {
            if(i < p1Coefficients.length)
            {
                newCoefficients[i] += p1Coefficients[i];
            }

            if(i < p2Coefficients.length)
            {
                newCoefficients[i] += p2Coefficients[i];
            }
        }

        return new Polynomial(newCoefficients);
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2)
    {
        Polynomial inverted = multiply(p2, -1);
        return add(p1, inverted);
    }

    public static Polynomial multiply(Polynomial p, double constant)
    {
        double[] coefficients = p.getCoefficients();
        double[] newCoefficients = new double[coefficients.length];

        for(int i = 0; i < coefficients.length; i++)
        {
            newCoefficients[i] = coefficients[i] * constant;
        }

        return new Polynomial(newCoefficients);
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2)
    {
        double[] p1Coefficients = p1.getCoefficients();
        double[] p2Coefficients = p2.getCoefficients();

        double[] newCoefficients = new double[p1Coefficients.length + p2Coefficients.length];

        for(int i = 0; i < p1Coefficients.length; i++)
        {
            for(int j = 0; j < p2Coefficients.length; j++)
            {
                newCoefficients[i + j] = p1Coefficients[i] * p2Coefficients[j];
            }
        }

        return new Polynomial(newCoefficients);
    }

    public static Polynomial divide(Polynomial p, double constant)
    {
        return multiply(p, 1 / constant);
    }

}
