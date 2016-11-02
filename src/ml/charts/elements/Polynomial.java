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
}
