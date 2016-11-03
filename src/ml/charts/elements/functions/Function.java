package ml.charts.elements.functions;

import ml.charts.DrawableImage;
import ml.charts.Graph;
import ml.charts.elements.Graphable;

public abstract class Function implements Graphable
{
    public abstract double getValue(double x);

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

    public void draw(Graph g, DrawableImage img, int color)
    {
        int width = g.getWidth();
        int height = g.getHeight();

        double[] coords = g.getCoordinateFromPixel(new int[] {0, 0});
        double result = getValue(coords[0]);

        int[] lastPixel = g.getPixelFromCoordinate(new double[] {coords[0], result});

        for(int i = 1; i < width; i++)
        {
            coords = g.getCoordinateFromPixel(new int[] {i, 0});
            result = getValue(coords[0]);

            int[] pixel = g.getPixelFromCoordinate(new double[] {coords[0], result});

            if(pixel[0] >= 0 && pixel[0] < width && pixel[1] >= 0 && pixel[1] < height)
            {
                if(lastPixel[0] >= 0 && lastPixel[0] < width && lastPixel[1] >= 0 && lastPixel[1] < height)
                {
                    //draw lines instead of points to make the function look smooth
                    img.line(lastPixel[0], lastPixel[1], pixel[0], pixel[1], color);
                }
            }

            lastPixel = pixel;
        }
    }

    public static Function add(Function f1, Function f2)
    {
        return new AddedFunction(f1, f2);
    }

    public static Function subtract(Function f1, Function f2)
    {
        return new SubtractedFunction(f1, f2);
    }

    public static Function multiply(Function f1, Function f2)
    {
        return new MultipliedFunction(f1, f2);
    }

    public static Function divide(Function f1, Function f2)
    {
        return new DividedFunction(f1, f2);
    }

    public static Function pow(Function f1, Function f2)
    {
        return new PowerFunction(f1, f2);
    }

    public static Function concatenate(Function f1, Function f2)
    {
        return new ConcatenatedFunction(f1, f2);
    }
}
