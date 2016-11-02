package ml.charts.elements;

import ml.charts.DrawableImage;
import ml.charts.Graph;

public abstract class Function extends AbstractElement
{
    public abstract double getValue(double x);

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
