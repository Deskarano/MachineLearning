package ml.charts.elements;

import ml.charts.DrawableImage;
import ml.charts.Graph;
import ml.matrix.Matrix;
import ml.regression.LinearRegression;

import java.util.Arrays;

public class Polynomial extends AbstractElement
{
    private double[] coefficients;
    private int color;

    public Polynomial(double[] coefficients, int color)
    {
        this.coefficients = coefficients;
        this.color = color;
    }

    public Polynomial(LinearRegression l, int color)
    {
        this(Matrix.transpose(l.getCoefficients()).asArray()[0], color);
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
    public void draw(Graph g, DrawableImage img)
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
                System.out.println(i);
            }

            lastPixel = pixel;
        }
    }
}
