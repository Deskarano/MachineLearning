package ml.matrix;

public class Vector extends Matrix
{
    public Vector(int dimension)
    {
        super(dimension, 1);
    }

    public Vector(double[] contents)
    {
        super(contents.length, 1);

        for(int i = 0; i < contents.length; i++)
        {
            set(i, contents[i]);
        }
    }

    public void set(int i, double value)
    {
        super.set(i, 0, value);
    }

    public double get(int i)
    {
        return super.get(i, 0);
    }

    public int getDimension()
    {
        return super.getRows();
    }
}
