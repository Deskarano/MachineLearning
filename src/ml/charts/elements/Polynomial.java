package ml.charts.elements;

import ml.charts.DrawableImage;
import ml.charts.Graph;
import ml.matrix.Matrix;
import ml.regression.LinearRegression;

public class Polynomial extends AbstractElement
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

    public double getValue(double x)
    {
        double sum = 0;

        for(int i = 0; i < coefficients.length; i++)
        {
            sum += coefficients[i] * Math.pow(x, i);
        }

        return sum;
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
    public double[] getPreferredDomain()
    {
        return null;
    }

    @Override
    public double[] getPreferredRange()
    {
        return null;
    }

    @Override
    public void draw(Graph g, DrawableImage img, int color)
    {
        double[] initCoords = g.getCoordinateFromPixel(new int[] {0, 0});
        double initResult = getValue(initCoords[0]);

        int[] lastPixel = g.getPixelFromCoordinate(new double[] {initCoords[0], initResult});

        for(int i = 1; i < g.getWidth(); i++)
        {
            double[] coords = g.getCoordinateFromPixel(new int[] {i, 0});
            double result = getValue(coords[0]);

            int[] pixel = g.getPixelFromCoordinate(new double[] {coords[0], result});

            try
            {
                img.line(lastPixel[0], lastPixel[1], pixel[0], pixel[1], color);
            }
            catch(Exception e)
            {
                //TODO: implement range checking before drawing the line
                //System.out.println(i);
            }

            lastPixel = pixel;
        }
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

    public static Polynomial divide(Polynomial p, double constant)
    {
        return multiply(p, 1 / constant);
    }
}
