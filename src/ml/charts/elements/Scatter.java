package ml.charts.elements;

import ml.charts.DrawableImage;
import ml.charts.Graph;
import ml.charts.exceptions.DataFormatException;
import ml.matrix.Matrix;


public class Scatter implements Graphable
{
    private double[][] data;

    private int pointSize;
    private int color;
    private boolean filled;

    public Scatter(double[][] data) throws DataFormatException
    {
        this(data, 3, 0xFFFF0000, true);
    }

    public Scatter(double[][] data, int pointSize, int color, boolean filled) throws DataFormatException
    {
        if(data.length > 2)
        {
            throw new DataFormatException("Data must have only 2 rows");
        }

        this.data = data;

        this.pointSize = pointSize;
        this.color = color;
        this.filled = filled;
    }


    @Override
    public double[] getPreferredDomain()
    {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;

        for(double d : data[0])
        {
            if(d < minX) minX = d;
            if(d > maxX) maxX = d;
        }

        return new double[]{minX, maxX};
    }

    @Override
    public double[] getPreferredRange()
    {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for(double d : data[1])
        {
            if(d < minY) minY = d;
            if(d > maxY) maxY = d;
        }

        return new double[]{minY, maxY};
    }

    @Override
    public void draw(Graph g, DrawableImage img, int color)
    {
        double[][] transposed = Matrix.transpose(new Matrix(data)).asArray();

        for(double[] coord : transposed)
        {
            int[] pixel = g.getPixelFromCoordinate(coord);

            img.circle(pixel[0], pixel[1], pointSize, color, filled);

        }
    }
}
